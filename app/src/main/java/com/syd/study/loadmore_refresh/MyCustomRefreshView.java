package com.syd.study.loadmore_refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.syd.study.R;
import com.syd.study.util.L;

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
    // 刷新提醒控件
    private View viewRefresh;
    // viewRefresh 的高度
    private int viewRefreshHideHeight;
    private MarginLayoutParams marginLayoutParams;

    ListView listView;
    boolean loadOnce = false;
    private float downY;
    private int currentStatus = STATUS_COMMON;
    private int lastCurrentStatus;
    boolean ableToPull = false;

    public MyCustomRefreshView(Context context) {
        this(context, null);
    }


    public MyCustomRefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        slopDistance = ViewConfiguration.get(context).getScaledTouchSlop();
        viewRefresh = View.inflate(context, R.layout.pull_to_refresh, null);
        addView(viewRefresh, 0);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        L.e("onLayout","w");
        super.onLayout(changed, l, t, r, b);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e("onSizeChanged","===");
        super.onSizeChanged(w, h, oldw, oldh);
        View view = getChildAt(1);
        float height = view.getMeasuredHeight();
//        if (!loadOnce) {
//            listView = (ListView) getChildAt(1);
//            if (listView != null) {
//                listView.setOnTouchListener(this);
//            }
//            viewRefreshHideHeight = -viewRefresh.getHeight();
//            marginLayoutParams = (MarginLayoutParams) viewRefresh.getLayoutParams();
//            // 目的是到刷新控件外面去
//            marginLayoutParams.topMargin = viewRefreshHideHeight;
//            viewRefresh.setLayoutParams(marginLayoutParams);
//            loadOnce = true;
//        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("onDraw","===");
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("onMaseur()","r");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 表示可下拉
        isPullAction(event);
        if (ableToPull) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downY = event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float moveDistance = event.getRawY() - downY;
                    // 说明手指上滑，或者滑动距离不够 不做任何处理
                    if (moveDistance<=0 || marginLayoutParams.topMargin<= viewRefreshHideHeight){
                        return false;
                    }
                    if (moveDistance<slopDistance){
                        return false;
                    }
//                    if (moveDistance <= 0 || moveDistance < slopDistance) {
//                        return false;
//                    }
                    if (currentStatus != STATUS_REFRESHING) {
                        if (marginLayoutParams.topMargin > 0) {
                            currentStatus = STATUS_RELEASE_REFRESH;
                        } else {
                            currentStatus = STATUS_PULL;
                        }
                        marginLayoutParams.topMargin = ((int)moveDistance / 2 + marginLayoutParams.topMargin);
                        viewRefresh.setLayoutParams(marginLayoutParams);

                    }
                    break;


            }
        }

        return false;
    }

    /**
     * 判断是否是下拉动作
     *
     * @param event MotionEvent
     * @return 是否是下拉
     */
    public boolean isPullAction(MotionEvent event) {
//        View childView = listView.getChildAt(0);
//        if (childView != null) {
//
//            return listView.getFirstVisiblePosition() == 0 && childView.getTop() == 0;
//        } else {
//
//
//            return true;
//        }

        View firstChild = listView.getChildAt(0);
        if (firstChild != null) {
            int firstVisiblePos = listView.getFirstVisiblePosition();
            L.e("setIsAbleToPull"+Math.random(),"Top:"+firstChild.getTop());
            if (firstVisiblePos == 0 && firstChild.getTop() == 0) {
                if (!ableToPull) {
                    downY = event.getRawY();
                }
                // 如果首个元素的上边缘，距离父布局值为0，就说明ListView滚动到了最顶部，此时应该允许下拉刷新
                ableToPull = true;
            } else {
                if (marginLayoutParams.topMargin != viewRefreshHideHeight) {
                    marginLayoutParams.topMargin = viewRefreshHideHeight;
                    viewRefresh.setLayoutParams(marginLayoutParams);
                }
                ableToPull = false;
            }
        } else {
            // 如果ListView中没有元素，也应该允许下拉刷新
            ableToPull = true;
        }
        return false;
    }

}
