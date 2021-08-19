package com.study;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * date: 2020/7/23 17:47
 *
 * @author syd
 * @version 1.0
 */
public class FlowLayout1 extends ViewGroup {
    // 记录所有的行，一行一行的储存
    private List<List<View>> allLines = new ArrayList<>();
    // 记录每一行的行高，用于 layout
    private List<Integer> lineHeights = new ArrayList<>();

    // 保存一行中的所有 view
    List<View> lineViews = new ArrayList<>();
    int mHorizontalSpacing = 30;
    int mVerticalSpacing = 30;

    public FlowLayout1(Context context) {
        super(context);
    }

    public FlowLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        lineViews.clear();
        lineHeights.clear();
        allLines.clear();
        int childCount = getChildCount();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        int lineWidthUsed = paddingLeft + paddingRight;
        int lineHeight = 0;


        int parentNeededHeight = 0;
        int parentNeededWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLP = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    paddingLeft + paddingRight, childLP.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    paddingTop + paddingBottom, childLP.height);
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            // 获取子View 的宽高

            int childMeasuredWidth = childView.getMeasuredWidth();
            int childMeasureHeight = childView.getMeasuredHeight();
            // 通过宽度来判断是否需要换行，通过换行后的每行的行高来获取整个 viewGroup 的行高
            if (childMeasuredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {

                allLines.add(lineViews);
                lineHeights.add(lineHeight);
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);
                parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;

                lineViews = new ArrayList<>();
                lineWidthUsed = paddingLeft + paddingRight;
                lineHeight = 0;
            }

            lineViews.add(childView);
            // 每行都会有自己的宽和高
            lineWidthUsed = lineWidthUsed + childMeasuredWidth + mHorizontalSpacing;
            lineHeight = Math.max(lineHeight, childMeasureHeight);
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeededHeight;
        setMeasuredDimension(realWidth, realHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 获取 layout 中的行数
        int lineCount = allLines.size() + 1;
        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        for (int i = 0; i < lineCount; i++) {
            if (allLines.size() == 0) {
                for (int j = 0; j < lineViews.size(); j++) {
                    View view = lineViews.get(j);
                    view.layout(curL, curT, curL + view.getMeasuredWidth(),
                            curT + view.getMeasuredHeight());
                    curL += view.getMeasuredWidth() + mHorizontalSpacing;
                }
                return;
            }
            if (i < lineCount - 1) {
                for (int j = 0; j < allLines.get(i).size(); j++) {
                    View view = allLines.get(i).get(j);
                    view.layout(curL, curT, curL + view.getMeasuredWidth(),
                            curT + view.getMeasuredHeight());
                    curL += view.getMeasuredWidth() + mHorizontalSpacing;
                }
                curT += lineHeights.get(i) + mVerticalSpacing;
                curL = getPaddingLeft();
            } else {

                for (int j = 0; j < lineViews.size(); j++) {
                    View view = lineViews.get(j);
                    view.layout(curL, curT, curL + view.getMeasuredWidth(),
                            curT + view.getMeasuredHeight());
                    curL += view.getMeasuredWidth() + mHorizontalSpacing;
                }
            }


        }

    }

}
