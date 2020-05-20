package com.study.testuses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.study.R;

import androidx.annotation.Nullable;

/**
 * 说明：$
 * <p>
 * date: 2020/4/30 17:28
 *
 * @author syd
 * @version 1.0
 */
public class TestView extends ViewGroup {
    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Bitmap bitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.fangdong);
        int width = bitmapSource.getWidth();
        int height = bitmapSource.getHeight();
        int size = Math.min(width,height);
        Bitmap b =Bitmap.createBitmap(bitmapSource,1,1,size-2,size-2);
        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(b, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        canvas.drawCircle((size-2)/2,(size-2)/2,(size-2)/2,paint);
//                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),b);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
