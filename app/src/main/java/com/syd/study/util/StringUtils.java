package com.syd.study.util;


import android.app.Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理
 * data 创建时间 2019/7/9 16:32
 *
 * @author sunyidong
 * @version 1.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class StringUtils {
    public static String getStringMayNull(String str) {
        if (null == str) {
            return "";
        }
        return str;
    }

    public static boolean isLengthBiggerZero(String str) {
        return getStringMayNull(str).length() > 0;
    }

    /**
     * 用于判断字符串是否为空
     *
     * @param s 字符串
     * @return 是否为 null 或者 长度为 0
     */
    public static boolean isEmptyAfterTrim(String s) {
        if (null == s)
            return true;
        return s.trim().length() == 0;
    }


    /**
     * 判断是否是6位
     *
     * @param activity Activity
     * @param pass     String
     * @return 是否
     */
    public static boolean is6Num(Activity activity, String pass) {
        if (pass != null) {
            return pass.length() >= 6;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是正确的手机号码 17,15,18,13,
     *
     * @param mobiles 手机号码
     * @return  格式是否正确
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
