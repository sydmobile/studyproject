package com.syd.study.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.util.L;

import androidx.annotation.Nullable;

/**
 * 说明:实验构造函数
 * <p>
 * date: 2019/8/28 14:44
 *
 * @author syd
 * @version 1.0
 */
@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {
    private static final String TAG = "CustomTextView";
    public CustomTextView(Context context) {
        super(context);
        L.e(TAG,"First Constructor");
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,android.R.attr.textViewStyle);
        L.e(TAG,"Second Constructor");
        for(int i=0;i<attrs.getAttributeCount();i++){

            L.e(attrs.getAttributeName(i),attrs.getAttributeValue(i)+"");
        }

    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        L.e(TAG,"Third Constructor");
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        L.e(TAG,"Fourth Constructor");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
    }
}
