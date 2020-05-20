package com.study.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.study.StudyApplication;

/**
 * 说明：网络帮助类
 * <p>
 * date: 2020/5/2 12:01
 *
 * @author syd
 * @version 1.0
 */
public class NetWorkUtils {

    /**
     * 判断当前网络是否可用
     * @return boolean
     */
    public static boolean isNetWorkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) StudyApplication.getAppInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected();

    }
}
