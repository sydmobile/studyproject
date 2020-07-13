package com.study.study_module.material;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 说明：$
 * <p>
 * date: 2020/7/7 16:46
 *
 * @author syd
 * @version 1.0
 */
public class DependencyView extends androidx.appcompat.widget.AppCompatButton {
    int lastX, lastY;

    public DependencyView(Context context) {
        super(context);
    }

    public DependencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DependencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                CoordinatorLayout.MarginLayoutParams layoutParams =
                        (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                int left = layoutParams.leftMargin + x - lastX;
                int top = layoutParams.topMargin + y - lastY;

                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);
                requestLayout();
                break;
            }
        }
        lastX = x;
        lastY = y;
        return true;


    }

}
