package com.study.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;

import com.study.StudyApplication;

/**
 * 说明：屏幕帮助类
 * <p>
 * date: 2020/4/30 9:04
 *
 * @author syd
 * @version 1.0
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ScreenUtil {

    private static DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
    private static DisplayMetrics outMetrics = new DisplayMetrics();

    /**
     * 获取逻辑密度 160dpi 返回值为 1
     *
     * @return density
     */
    public static float getDensity() {
        return displayMetrics.density;
    }


    /**
     * dots-per-inch
     *
     * @return 160 240 ...
     */
    public static int getDensityDpi() {
        return displayMetrics.densityDpi;
    }

    /**
     * 与字体有关，正常情况下和 density 一样
     *
     * @return scaleDensity
     */
    public static float getScaleDensity() {
        return displayMetrics.scaledDensity;
    }

    /**
     * 获取宽度 px
     *
     * @return px
     */
    public static int getWidthPixels() {
        return displayMetrics.widthPixels;
    }

    /**
     * 获取高度 px
     * 这个高度是屏幕真正的分辨率减去状态栏和导航栏（不论有还是没有）
     *
     * @return px
     */
    public static int getHeightPixels() {
        return displayMetrics.heightPixels;
    }

    /**
     * Y 方向上的屏幕密度
     *
     * @return dpi
     */
    public static float getYdpi() {
        return displayMetrics.ydpi;
    }

    /**
     * X 方向上的屏幕密度
     *
     * @return dpi
     */
    public static float getXdpi() {
        return displayMetrics.xdpi;
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int height = 0;
        int resourceId = StudyApplication.getAppInstance().getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return StudyApplication.getAppInstance().getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * 获取系统栏高度
     * 包括：导航栏、状态栏
     * 获取的高度是固定的无论是否有导航栏和状态栏
     *
     * @return 导航栏高度
     */
    public static int getNavigationBarHeight() {
        int resourceId;
        int rid = StudyApplication.getAppInstance().getResources().getIdentifier(
                "config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = StudyApplication.getAppInstance().getResources().getIdentifier(
                    "navigation_bar_height", "dimen", "android");
            return StudyApplication.getAppInstance().getResources().getDimensionPixelSize(resourceId);
        } else
            return 0;
    }

    /**
     * 获取真实的宽高（包含了状态栏、导航条，也就是手机的分辨率）
     *
     * @param activity Activity
     * @return int[] 宽高
     */
    public static int[] getRealSize(Activity activity) {
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        return new int[]{outMetrics.heightPixels, outMetrics.widthPixels};
    }


    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity 当前页面
     * @return 截图
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int[] size = getRealSize(activity);
        Bitmap bp;
        bp = Bitmap.createBitmap(bmp, 0, 0, size[0], size[1]);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前设备 sw 的值
     *
     * @return sw 值
     */
    public static int getSMDP() {

        return StudyApplication.getAppInstance().getResources().getConfiguration().smallestScreenWidthDp;
    }
}
