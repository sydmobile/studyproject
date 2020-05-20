package com.study.utils.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.study.R;

import java.security.MessageDigest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

/**
 * 说明：Glide 工具类
 * <p>
 * date: 2019/4/17 13:52
 *
 * @author sunyidong
 * @version 1.0
 */
@SuppressWarnings("unused")
public class GlideUtils {
    private static RequestOptions requestOptions;
    private static RequestOptions requestOptionsCircle;
    // Glide 内部都是单例模式，所以不会重复创建 Glide，放心大胆的 Glide.with() 就行了
    // 传入的 context 在内部也是 context.getApplicationContext
    /**
     * 普通加载图片
     *
     * @param context 上下文
     * @param url     img url
     * @param iv      ImageView
     */
    public static void loadImg(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).apply(getRequestOptions()).into(iv);
    }

    /**
     * 加载本地图片
     * @param context context
     * @param res 本地资源
     * @param iv iv
     */
    public static void loadImg(Context context, int res, ImageView iv) {
        Glide.with(context).load(res).apply(getRequestOptions()).into(iv);
    }

    /**
     * 加载本地图片，无 Option
     * @param context context
     * @param res 本地资源
     * @param iv iv
     */
    public static void loadImgNoOptions(Context context, int res, ImageView iv) {
        Glide.with(context).load(res).into(iv);
    }


    public static void loadCircleImg(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).apply(getCircleRequestOptions()).into(iv);
    }

    public static void loadRoundImg(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).error(R.mipmap.ic_launcher_round).placeholder(R.mipmap.ic_launcher_round).into(new CircleBitmapTarget(iv));
    }

    /**
     * 获取 请求选项
     *
     * @return requestOptions
     */
    private static RequestOptions getRequestOptions() {
        if (requestOptions == null) {
            synchronized (GlideUtils.class) {
                if (requestOptions == null) {
                    requestOptions = new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher);
                }
            }
        }
        return requestOptions;
    }

    private static RequestOptions getCircleRequestOptions() {
        if (requestOptionsCircle == null) {
            synchronized (GlideUtils.class) {
                if (requestOptionsCircle == null) {
                    requestOptionsCircle =
                            RequestOptions.bitmapTransform(new GlideCircleTransform())
                                    .placeholder(R.mipmap.ic_launcher)
                                    .error(R.mipmap.ic_launcher);
                }

            }
        }
        return requestOptionsCircle;
    }


}


class CircleBitmapTarget extends ImageViewTarget<Drawable> {

    CircleBitmapTarget(ImageView view) {
        super(view);
    }


    /**
     * 从指定路径加载的 Bitmap
     *
     * @param resource 资源
     */
    @Override
    protected void setResource(@Nullable Drawable resource) {
        bindCircleBitmapToImageView(resource);
    }

    /**
     * onLoadFailed 和 onLoadStarted 调用该方法。用于设置默认的图片和异常图片
     * 设置默认图片
     *
     * @param drawable 绘制对象
     */
    @Override
    public void setDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            bindCircleBitmapToImageView(drawable);
        } else {
            view.setImageDrawable(drawable);
        }
    }

    /**
     * 通过 RoundedBitmapDrawable 绘制圆形 Bitmap，加载 ImageView
     *
     * @param drawable drawable
     */
    private void bindCircleBitmapToImageView(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            RoundedBitmapDrawable bitmapDrawable =
                    RoundedBitmapDrawableFactory.create(view.getContext().getResources(),
                            ((BitmapDrawable) drawable).getBitmap());
            bitmapDrawable.setCircular(true);
            view.setImageDrawable(bitmapDrawable);
        }

    }

}

class GlideCircleTransform extends BitmapTransformation {

    private Paint mBorderPaint;
    private float mBorderWidth;

    GlideCircleTransform() {
        this(1);
    }

    private GlideCircleTransform(int borderWidth) {
        super();
        mBorderWidth = Resources.getSystem().getDisplayMetrics().density * borderWidth;
        mBorderPaint = new Paint();
        mBorderPaint.setDither(true);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(Color.parseColor("#CBD1d9"));
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
    }


    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform,
                               int outWidth, int outHeight) {
        return circleCrop(toTransform);
    }

    private Bitmap circleCrop(Bitmap source) {
        if (source == null) return null;
        int size = (int) (Math.min(source.getWidth(), source.getHeight()) - (mBorderWidth / 2));
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result;
        result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP,
                BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        if (mBorderPaint != null) {
            float borderRadius = r - mBorderWidth / 2;
            canvas.drawCircle(r, r, borderRadius, mBorderPaint);
        }
        return result;
    }


    public String getId() {
        return getClass().getName();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }


}
