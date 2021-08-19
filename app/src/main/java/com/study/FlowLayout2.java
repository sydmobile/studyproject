package com.study;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>
 * date: 2020/7/27 10:00
 *
 * @author syd
 * @version 1.0
 */
public class FlowLayout2 extends ViewGroup {

    public FlowLayout2(Context context) {
        super(context);
    }

    public FlowLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int usedWidth = getPaddingLeft() + getPaddingRight();
        int lineMaxHeight = 0;
        int totalHeight = getPaddingTop() + getPaddingBottom();
        int lineMaxWidth = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

            if (usedWidth + childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin > MeasureSpec.getSize(widthMeasureSpec)) {
                lineMaxWidth = Math.max(lineMaxWidth, usedWidth);
                usedWidth = getPaddingLeft() + getPaddingRight();
                totalHeight += lineMaxHeight;
                lineMaxHeight = 0;
            }
            usedWidth += childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            lineMaxHeight = Math.max(lineMaxHeight,
                    childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);

            if (i == count - 1) {
                lineMaxWidth = Math.max(lineMaxHeight, usedWidth);
                totalHeight += lineMaxHeight;
            }

        }
        setMeasuredDimension(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY ?
                        MeasureSpec.getSize(widthMeasureSpec) : lineMaxWidth,
                MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY ?
                        MeasureSpec.getSize(heightMeasureSpec) :
                        totalHeight + getPaddingTop() + getPaddingRight());

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int usedWidth = getPaddingLeft() + getPaddingRight();
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int maxHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

            if (usedWidth + lp.leftMargin + lp.rightMargin + childView.getMeasuredWidth() > getMeasuredWidth()) {
                left = getPaddingLeft();
                top += maxHeight;
                usedWidth = getPaddingLeft() + getPaddingRight();
                maxHeight = 0;
            }

            childView.layout(left + lp.leftMargin, top + lp.topMargin,
                    left + lp.leftMargin + childView.getMeasuredWidth(),
                    top + lp.topMargin + childView.getMeasuredHeight());
            left += lp.leftMargin + childView.getMeasuredWidth() + lp.rightMargin;
            maxHeight = Math.max(maxHeight,
                    childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
            usedWidth += lp.leftMargin + lp.rightMargin + childView.getMeasuredWidth();

        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
