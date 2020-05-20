package com.study.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.study.StudyApplication;

import androidx.core.app.ActivityCompat;

/**
 * Created by sunyidong on 2016/10/14.
 * 获取手机的相关信息
 */

public class PhoneAPIs {
//    public static String getVersion() {
////        //版本号
////        AppInfor appInfor = new AppInfor(BaseApplication.getInstance());
////        String version = String.valueOf(appInfor.getVersionCode());
////        return version;
////    }
////
////    //版本名 1.1.7
////    public static String getVersionName() {
////        //版本号
////        AppInfor appInfor = new AppInfor(BaseApplication.getInstance());
////        String version = String.valueOf(appInfor.getVersionName());
////        return version;
////    }

    public static String getMtype() {
        //手机型号
        return android.os.Build.MODEL;
    }

    public static String getMtyb() {
        //手机品牌
        return android.os.Build.BRAND;
    }

    public static String getImei() {
//        return UUID.randomUUID().toString();
        //移动电话设备识别码
        TelephonyManager tm = (TelephonyManager) StudyApplication.getAppInstance().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(StudyApplication.getAppInstance(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return null;
        } else {
            if (tm != null) {

                return tm.getDeviceId();
            } else {
                return null;
            }
        }
    }


}
