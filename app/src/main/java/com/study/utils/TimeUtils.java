package com.study.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 说明：时间工具类
 * <p>
 * date: 2020/4/22 16:47
 *
 * @author syd
 * @version 1.0
 */
public class TimeUtils {

    /**
     * 获取当前时间
     * @param format  yyyy-MM-dd HH:mm:ss
     * @return 当前时间
     */
    public static String getCurrentTime(String format){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }


}
