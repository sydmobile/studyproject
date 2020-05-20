package com.study.study_module.loadmore_refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.R;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

/**
 * 说明：自定义下拉刷新控件
 *
 * <p>
 * date: 2019/8/30 12:05
 *
 * @author syd
 * @version 1.0
 */
public class MyCustomRefreshView extends LinearLayout implements View.OnTouchListener {

    // 记录滑动前手机可以移动的最大距离
    int slopDistance;
    // 释放刷新状态
    private static int STATUS_RELEASE_REFRESH = 0;
    // 下拉状态
    private static int STATUS_PULL = 1;
    // 刷新中状态
    private static int STATUS_REFRESHING = 2;
    // 普通状态
    private static int STATUS_COMMON = 3;
    // 刷新提示控件
    private View viewRefresh;
    // viewRefresh 隐藏高度，当 marginTo 是这个值得时候 viewRefresh 会彻底隐藏
    private int viewRefreshHideHeight;
    // viewRefresh 的 MarginLayoutParams
    private MarginLayoutParams marginLayoutParams;

    ListView listView;
    // 记录是否加载过一次
    boolean loadOnce = false;
    // 记录触摸在屏幕上的 Y 坐标
    private float downY;
    private int currentStatus = STATUS_COMMON;
    private int lastCurrentStatus = STATUS_COMMON;
    // 当前是否可以下拉，只有 ListView 滚动到头的时候才允许下拉，否则就是普通的滚动 ListView
    boolean isAbleToPull = false;
    // 提示箭头
    private ImageView ivArrow;
    // 刷新状态提示
    private TextView tvRefreshStatus;
    // 刷新的一些信息
    private TextView tvRefreshInfo;
    // 刷新监听
    private PullToRefreshListener pullToRefreshListener;
    // 下拉后头部回滚的速度
    public static final int SCROLL_SPEED = -20;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            marginLayoutParams.topMargin = msg.what;
            viewRefresh.setLayoutParams(marginLayoutParams);
            if (currentStatus == STATUS_REFRESHING) {
                handler.sendEmptyMessageDelayed(-200, 2000);
            }
            if (msg.what == -200) {
                currentStatus = STATUS_COMMON;
                marginLayoutParams.topMargin = viewRefreshHideHeight;
                viewRefresh.setLayoutParams(marginLayoutParams);
            }
        }
    };

    public MyCustomRefreshView(Context context) {
        this(context, null);
    }


    public MyCustomRefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        slopDistance = ViewConfiguration.get(context).getScaledTouchSlop();
        viewRefresh = View.inflate(context, R.layout.pull_to_refresh, null);
        ivArrow = viewRefresh.findViewById(R.id.arrow);
        tvRefreshInfo = viewRefresh.findViewById(R.id.tv_updated_at);
        tvRefreshStatus = viewRefresh.findViewById(R.id.tv_description);
        addView(viewRefresh, 0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // 执行完布局后，获取子 View 的大小，用于刷新头
        if (!loadOnce) {
            listView = (ListView) getChildAt(1);
            if (listView != null) {
                listView.setOnTouchListener(this);

            }
            viewRefreshHideHeight = -viewRefresh.getHeight();
            marginLayoutParams = (MarginLayoutParams) viewRefresh.getLayoutParams();
            // 目的是让刷新控件到外面去
            marginLayoutParams.topMargin = viewRefreshHideHeight;
            loadOnce = true;
        }
        // 基于事件驱动
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 判断是否下拉
        setIsAbleToPull();
        if (isAbleToPull) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downY = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float moveDistance = event.getRawY() - downY;
                    // 说明手指上滑，或者滑动距离不够 不做任何处理
                    if (moveDistance < slopDistance) {
                        return false;
                    }

                    if (currentStatus != STATUS_REFRESHING) {

                        marginLayoutParams.topMargin =
                                ((int) moveDistance / 2 + viewRefreshHideHeight);
                        viewRefresh.setLayoutParams(marginLayoutParams);
                        if (marginLayoutParams.topMargin > 0) {
                            currentStatus = STATUS_RELEASE_REFRESH;
                        } else if (marginLayoutParams.topMargin > viewRefreshHideHeight) {
                            currentStatus = STATUS_PULL;
                        }
                    }
                    break;

                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                default:
                    if (currentStatus == STATUS_RELEASE_REFRESH) {
                        Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();
                        currentStatus = STATUS_REFRESHING;
                        refresh();
                    } else if (currentStatus == STATUS_PULL) {
                        release();
                    }
                    break;




            }

            if (currentStatus == STATUS_PULL || currentStatus == STATUS_RELEASE_REFRESH) {
                listView.setPressed(false);
                listView.setFocusable(false);
                listView.setFocusableInTouchMode(false);
                // 更新信息描述
                updateHeaderView();
                lastCurrentStatus = currentStatus;
                return true;
            }

        }

        return false;
    }

    public void release() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (marginLayoutParams.topMargin > viewRefreshHideHeight) {

                    handler.sendEmptyMessage(marginLayoutParams.topMargin - 20);
                } else {
                    handler.sendEmptyMessage(viewRefreshHideHeight);
                    cancel();
                }
            }
        }, 10, 20);

    }

    // 刷新状态
    public void refresh() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (marginLayoutParams.topMargin > 0) {

                    handler.sendEmptyMessage(marginLayoutParams.topMargin + SCROLL_SPEED);
                } else {
                    handler.sendEmptyMessage(0);
                    cancel();
                }
            }
        }, 10, 20);
    }

    /**
     * 更新刷新头控件信息
     */
    public void updateHeaderView() {
        if (lastCurrentStatus != currentStatus) {
            if (currentStatus == STATUS_RELEASE_REFRESH) {
                tvRefreshStatus.setText("释放刷新");
            }
            if (currentStatus == STATUS_PULL) {
                tvRefreshStatus.setText("下拉刷新");
            } else if (currentStatus == STATUS_REFRESHING) {
                tvRefreshStatus.setText("正在刷新");
            }
            rotateArrow();

        }

    }

    public void rotateArrow() {
        float pivotX = ivArrow.getWidth() / 2f;
        float pivotY = ivArrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if (currentStatus == STATUS_PULL) {
            fromDegrees = 180f;
            toDegrees = 360f;
        } else if (currentStatus == STATUS_RELEASE_REFRESH) {
            fromDegrees = 0f;
            toDegrees = 180f;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees, pivotX,
                pivotY);
        rotateAnimation.setDuration(100);
        rotateAnimation.setFillAfter(true);
        ivArrow.startAnimation(rotateAnimation);

    }

    /**
     * 判断是否是下拉动作
     * <p>
     * <p>
     * 根据 ListView 的状态来设定 isAbleToPull 是否应该下拉
     */
    public void setIsAbleToPull() {
        // 获取 ListView 的第一个 View
        View firstChild = listView.getChildAt(0);

        if (firstChild != null) {
            // 获取 listView 第一个可见的 item 的 位置
            int firstVisiblePos = listView.getFirstVisiblePosition();
            // 可见 item 为 0 并且 firstChild Top 位置为 0 说明可以执行下拉
            if (firstVisiblePos == 0 && firstChild.getTop() == 0) {
                // 如果首个元素的上边缘，距离父布局值为0，就说明ListView滚动到了最顶部，此时应该允许下拉刷新
                isAbleToPull = true;
            } else {
                // 这时候如果 topMargin 不合适的话，就要设置合适
                if (marginLayoutParams.topMargin != viewRefreshHideHeight) {
                    marginLayoutParams.topMargin = viewRefreshHideHeight;
                    viewRefresh.setLayoutParams(marginLayoutParams);
                }
                isAbleToPull = false;
            }
        } else {
            // 如果ListView中没有元素，也应该允许下拉刷新
            isAbleToPull = true;
        }
    }

    // 下拉刷新监听
    public interface PullToRefreshListener {

        /**
         * 刷新的时候回调，可以在这里做请求数据等业务逻辑
         */
        void onRefresh();

    }

}
