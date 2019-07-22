package com.syd.study.util;


import android.app.Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author Administrator
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
     * @param s String
     * @return 是否 null
     */
    public static boolean isEmptyAfterTrim(String s) {
        if (null == s)
            return true;
        return s.trim().length() == 0;
    }


    /**
     * 判断是否是6位数字
     *
     * @param activity activity
     * @param password 密码
     * @return 是否满足
     */
    public static boolean is6Num(Activity activity, String password) {
        if (password != null) {

            return password.length() >= 6;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是正确的手机号码 17,15,18,13,
     * @param mobiles 手机号
     * @return 是否符合手机号
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
