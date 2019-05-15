package com.syd.study.util;

import android.content.Context;

/**
 * 说明：获取窗口的一些高度
 * <p>
 * date: 2019/5/15 17:11
 *
 * @author syd
 * @version 1.0
 */
public class WindowsHelper {

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}


