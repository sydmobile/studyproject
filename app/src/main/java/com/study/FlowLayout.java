package com.study;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.study.entity.WXArticle;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

/**
 * <p>
 * date: 2020/7/23 10:57
 *
 * @author syd
 * @version 1.0
 */
public class FlowLayout extends ViewGroup {
    private List<View> listLine = new ArrayList<>();
    private List list = new ArrayList();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public static void main(String[] args) throws IOException {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        int usedWidth = getPaddingLeft() + getPaddingRight();
        int lineMaxHeight = 0;
        int specModeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int specSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int specSizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int lineWidth = 0;
        int lineHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);

            if (usedWidth + (childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin) > specSizeWidth) {
                lineWidth = Math.max(lineWidth, usedWidth);
                lineHeight += lineMaxHeight;
                usedWidth =
                        getPaddingLeft() + getPaddingRight() + childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                lineMaxHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            } else {
                usedWidth += childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                lineMaxHeight = Math.max(lineMaxHeight,
                        childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);

            }

            if (i == childCount - 1) {
                lineHeight += lineMaxHeight;
                lineWidth = Math.max(lineWidth, usedWidth);

            }

        }

        setMeasuredDimension(specModeWidth == MeasureSpec.EXACTLY ? specSizeWidth : lineWidth,
                specModeHeight == MeasureSpec.EXACTLY ?
                        specSizeHeight :
                        lineHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int usedWidth = getPaddingLeft() + getPaddingRight();
        int usedHeight = getPaddingTop();
        int left = getPaddingLeft();
        int maxLineHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int viewWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int viewHeight = childView.getMeasuredHeight();
            if (usedWidth + viewWidth > getMeasuredWidth()) {
                usedHeight += maxLineHeight;
                maxLineHeight = 0;
                usedWidth = getPaddingLeft() + getPaddingRight();
                left = getPaddingLeft();
            }
            left += lp.leftMargin;
            childView.layout(left, usedHeight + lp.topMargin, childView.getMeasuredWidth() + left
                    , usedHeight + lp.topMargin + childView.getMeasuredHeight());
            left += childView.getMeasuredWidth() + lp.rightMargin;
            maxLineHeight = Math.max(maxLineHeight,
                    childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
            usedWidth += lp.leftMargin + lp.rightMargin + childView.getMeasuredWidth();
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /*

     **/

    // 主要是为了提供一个名字，方便快速的找到错误
    public class AgeOutOfBoundsException extends Exception {
        public AgeOutOfBoundsException() {
        }

        public AgeOutOfBoundsException(String message) {
            super(message);
        }


    }

}
