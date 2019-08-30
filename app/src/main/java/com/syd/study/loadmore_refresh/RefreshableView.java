package com.syd.study.loadmore_refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.syd.study.R;

import androidx.annotation.Nullable;

import static java.lang.Thread.sleep;

/**
 * 说明：刷新功能 View
 * <p>
 * date: 2019/8/29 14:15
 *
 * @author syd
 * @version 1.0
 */
public class RefreshableView extends LinearLayout implements View.OnTouchListener {
    // 下拉状态
    public static final int STATUS_PULL_TO_REFRESH = 0;
    // 释放立即刷新状态
    public static final int STATUS_RELEASE_TO_REFRESH = 1;
    // 正在刷新状态
    public static final int STATUS_REFRESHING = 2;
    // 刷新完成或者未刷新状态
    public static final int STATUS_REFRESH_FINISHED = 3;
    // 下拉头部回滚的速度
    public static final int SCROLL_SPEED = -20;
    // 一分钟的毫秒值，用于判断上次的更新时间
    public static final long ONE_MINUTE = 60 * 1000;
    //一小时的毫秒值，用于判断上次更新时间
    public static final long ONE_HOUR = 60 * ONE_MINUTE;
    // 一天的毫秒值，用于判断上次更新时间
    public static final long ONE_DAY = 24 * ONE_HOUR;
    // 上次更新时间的字符串常量，用作作为 SharedPreference 的键值
    private static final String UPDATED_AT = "updated_at";
    // 下拉刷新的回调接口
    private PullToRefreshListener mListener;
    // 用于储存上次更新时间
    private SharedPreferences preferences;
    // 下拉头的 View
    private View header;
    // 需要去下拉刷新的 ListView
    private ListView listView;
    // 刷新时显示的进度条
    private ProgressBar progressBar;
    // 提示下拉和释放的箭头
    private ImageView arrow;
    // 上次更新时间的文字描述
    private TextView tvUpdateAt;
    // 下拉头的布局参数
    private MarginLayoutParams headerLayoutParams;
    // 上次更新时间的毫秒值
    private long lastUpdateTime;
    // 为了防止不同界面的下拉刷新在上次更新时间上互相有冲突，用 id 来做区分
    private int mId = -1;
    // 下拉头的高度
    private int hideHeaderHeight;
    //当前处理什么状态，可选值有 STATUS_PULL_TO_REFRESH,STATUS_RELEASE_TO_REFRESH 等等
    private int currentStatus = STATUS_REFRESH_FINISHED;
    // 记录上一次的状态是什么，避免重复操作
    private int lastStatus = currentStatus;
    // 手机按下时的屏幕纵坐标
    private float yDown;
    // 在被判断为滚动之前用户手机可以移动的最大值
    private int touchSlop;
    // 是否已经加载过一次 layout ，这里 onLayout 中的初始化只需加载一次
    private boolean loadOnce;
    // 当前是否可以下拉，只有 LIstView 滚动到头的时候才允许下拉
    private boolean ableToPull;
    // 描述信息
    private TextView tvDescription;

    public RefreshableView(Context context) {
        super(context);
    }

    /**
     * 下拉刷新控件的构造函数，会在运行时动态添加一个下拉头布局
     *
     * @param context 上下文
     * @param attrs   属性集合
     */
    public RefreshableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        header = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh, null, true);
        progressBar = header.findViewById(R.id.progress_bar);
        arrow = header.findViewById(R.id.arrow);
        tvDescription = header.findViewById(R.id.tv_description);
        tvUpdateAt = header.findViewById(R.id.tv_updated_at);

        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        refreshUpdateAtValue();
        setOrientation(VERTICAL);
        addView(header, 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !loadOnce) {
            hideHeaderHeight = -header.getHeight();
            headerLayoutParams = (MarginLayoutParams) header.getLayoutParams();
            headerLayoutParams.topMargin = hideHeaderHeight;
//            header.setLayoutParams(headerLayoutParams);
            listView = (ListView) getChildAt(1);
            listView.setOnTouchListener(this);
            loadOnce = true;
        }
    }

    /**
     * 当 ListView 被触摸时调用，其中处理了各种下拉刷新的具体逻辑
     *
     * @param v     View
     * @param event 动作事件
     * @return 是否消耗
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setIsAbleToPull(event);
        if (ableToPull) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    yDown = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float yMove = event.getRawY();
                    int distance = (int) (yMove - yDown);
                    // 如果手指是下滑状态，并且下拉头是完全隐藏的，就屏蔽下拉事件
                    if (distance <= 0 && headerLayoutParams.topMargin <= hideHeaderHeight) {
                        return false;
                    }
                    if (distance < touchSlop) {
                        return false;
                    }
                    if (currentStatus != STATUS_REFRESHING) {
                        if (headerLayoutParams.topMargin > 0) {
                            currentStatus = STATUS_RELEASE_TO_REFRESH;
                        } else {
                            currentStatus = STATUS_PULL_TO_REFRESH;
                        }
                        // 通过偏移下拉头的 toMargin 值来实现下拉效果
                        headerLayoutParams.topMargin = (distance / 2) + hideHeaderHeight;
                        header.setLayoutParams(headerLayoutParams);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    default:
                        if (currentStatus == STATUS_RELEASE_TO_REFRESH){
                            // 松手时如果是释放立即刷新状态，就去调用刷新任务
                            new RefreshingTask().execute();
                        }else if (currentStatus == STATUS_PULL_TO_REFRESH){
                            // 松手时如果是下拉状态，就去调用隐藏下拉头的任务
                            new HideHeaderTask().execute();
                        }
                        break;

            }
            // 时刻记得更新下拉头中的信息
            if (currentStatus == STATUS_PULL_TO_REFRESH || currentStatus == STATUS_RELEASE_TO_REFRESH){
                updateHeaderView();
                // 当前正处于下拉或释放状态，要让 ListView 失去焦点，否则被点击的那一项会一直处于选中状态
                listView.setPressed(false);
                listView.setFocusable(false);
                listView.setFocusableInTouchMode(false);
                lastStatus = currentStatus;
                // 当前正在处于下拉或者释放状态，通过返回 true 屏蔽掉 ListView 的滚动事件
                return true;
            }
        }
        return false;
    }

    /**
     * 给下拉刷新控件注册一个监听器
     * @param listener 监听器的实现
     * @param id 为了防止不同页面的下拉刷新在上次更新时间上互相有冲突，请不同界面在注册下拉刷新监听器的时候传入不同的 id
     */
    public void setOnRefreshListener(PullToRefreshListener listener,int id){
        mListener = listener;
        mId = id;
    }

    /**
     * 当所有的刷新逻辑完成后，记录调用一下，否则你的 ListView 将一直处于正在刷新状态
     */
    public void finishRefreshing(){
        currentStatus = STATUS_REFRESH_FINISHED;
        preferences.edit().putLong(UPDATED_AT+mId,System.currentTimeMillis()).commit();
        new HideHeaderTask().execute();
    }

    private void setIsAbleToPull(MotionEvent event){
        View firstChild = listView.getChildAt(0);
        if (firstChild!=null){
            int firstVisiblePos = listView.getFirstVisiblePosition();
            if (firstVisiblePos == 0 && firstChild.getTop() == 0){
                if (!ableToPull){
                    yDown = event.getRawY();
                }
                // 如果首个元素的上边缘，距离父布局值为 0 ，就说明 ListView 滚动到了最顶部，此时应该运行下拉刷新

                ableToPull = true;
            }else {
                if (headerLayoutParams.topMargin != hideHeaderHeight){
                    headerLayoutParams.topMargin = hideHeaderHeight;
                    header.setLayoutParams(headerLayoutParams);
                }
                ableToPull = false;
            }
        }else {
            // 若果 ListView 没有元素，也应该运行下拉刷新
            ableToPull = true;
        }

    }

    private void updateHeaderView(){
        if (lastStatus != currentStatus){
            if (currentStatus == STATUS_PULL_TO_REFRESH){
                tvDescription.setText("下拉刷新");
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(GONE);
                rotateArrow();
            }else if (currentStatus == STATUS_RELEASE_TO_REFRESH){
                tvDescription.setText("释放刷新");
                arrow.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                rotateArrow();
            }else if (currentStatus == STATUS_REFRESHING){
                tvDescription.setText("正在刷新");
                arrow.setVisibility(GONE);
                progressBar.setVisibility(VISIBLE);
                arrow.clearAnimation();
            }
            refreshUpdateAtValue();
        }
    }

    /**
     * 根据当前状态来旋转箭头
     */
    private void rotateArrow(){
        float pivotX = arrow.getWidth()/2f;
        float pivotY = arrow.getHeight()/2f;
        float formDegress = 0f;
        float toDegrees = 0f;
        if (currentStatus == STATUS_PULL_TO_REFRESH){
            formDegress = 180f;
            toDegrees = 360f;
        }else if (currentStatus == STATUS_RELEASE_TO_REFRESH){
            formDegress = 0f;
            toDegrees = 180f;
        }
        RotateAnimation animation = new RotateAnimation(formDegress,toDegrees,pivotX,pivotY);
        animation.setDuration(100);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }

    private void refreshUpdateAtValue(){
        lastUpdateTime = preferences.getLong(UPDATED_AT+mId,-1);
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdateTime;
        String updateAtValue = null;
        if (lastUpdateTime == -1){
            updateAtValue = "从未更新过";

        }else if (timePassed < 0){
            updateAtValue = "时间错误";
        }else if (timePassed<ONE_MINUTE){
            updateAtValue = "刚刚更新过";
        }
        tvUpdateAt.setText(updateAtValue);
    }

    class RefreshingTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            int topMargin = headerLayoutParams.topMargin;
            while (true) {
                topMargin = topMargin+SCROLL_SPEED;
                if (topMargin<=0){
                    topMargin = 0;
                    break;
                }
                publishProgress(topMargin);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            currentStatus = STATUS_REFRESHING;
            publishProgress(0);
            if (mListener !=null){
                mListener.onRefresh();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            updateHeaderView();
            headerLayoutParams.topMargin = values[0];
            header.setLayoutParams(headerLayoutParams);
        }
    }
    class HideHeaderTask extends AsyncTask<Void,Integer,Integer>{

        @Override
        protected Integer doInBackground(Void... voids) {
            int topMargin = headerLayoutParams.topMargin;
            while (true){
                topMargin = topMargin + SCROLL_SPEED;
                if (topMargin<=hideHeaderHeight){
                    topMargin = hideHeaderHeight;
                    break;
                }
                publishProgress(topMargin);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return topMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            headerLayoutParams.topMargin = values[0];
            header.setLayoutParams(headerLayoutParams);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            headerLayoutParams.topMargin = integer;
            header.setLayoutParams(headerLayoutParams);
            currentStatus = STATUS_REFRESH_FINISHED;
        }
    }

    public interface PullToRefreshListener{

        /**
         * 刷新时会去调用此方法，在方法内编写具体的刷新逻辑。注意此方法是在子线程中
         * 调用的，可以不必另外开线程来进行耗时操作。
         */
        void onRefresh();
    }


}
