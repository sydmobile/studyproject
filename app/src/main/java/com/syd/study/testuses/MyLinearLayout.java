package com.syd.study.testuses;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.syd.study.R;

import androidx.annotation.Nullable;

/**
 * 说明：d$
 * <p>
 * date: 2019/9/18 8:52
 *
 * @author syd
 * @version 1.0
 */
public class MyLinearLayout extends RelativeLayout {
    private static final String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG,"构造方法");
        LayoutInflater.from(context).inflate(R.layout.item_footer,this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e(TAG,"onLayout");
    }

    @Override
    public void draw(Canvas canvas) {
        Log.e(TAG,"draw");
        super.draw(canvas);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
    }
}
