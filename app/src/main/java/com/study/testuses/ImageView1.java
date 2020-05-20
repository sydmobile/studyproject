package com.study.testuses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.study.R;
import com.study.userful.util.L;
import com.study.utils.UnitUtils;

import androidx.annotation.Nullable;

/**
 * 说明：绘画
 * <p>
 * date: 2020/5/1 10:50
 *
 * @author syd
 * @version 1.0
 */
public class ImageView1 extends View {
    private Paint paint;
    private Bitmap bitmap;

    public ImageView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fangdong);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR));
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        L.e("onMeasure" + UnitUtils.px2dpByDensity(getContext(),
                MeasureSpec.getSize(widthMeasureSpec)));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        L.e("onLayout" + left + "==" + right);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        L.e("onLayout" + w + "==" + oldw);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        L.e("onDraw");
        int width = getWidth();
        int height = getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        L.e("width:" + width, "height:" + height);
        int cx = Math.min(width, height);
        canvas.drawCircle(cx / 2, cx / 2, cx / 2, paint);

    }
}
