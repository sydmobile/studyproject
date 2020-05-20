package com.study.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by sunyidong on 2016/7/28.
 * Gson解析的有关工具类
 */
@SuppressWarnings("unused")
public class GsonUtils {
    private static Gson gson = new Gson();
    public static <T> T jsonString2Bean(String jsonString, Class<T> cls) {
        try {
            return gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            Log.e("jsonStringToBean()", "异常:" + e.getMessage());
        }
        return null;
    }

    public static <T> List<T> jsonString2List(String jsonString, TypeToken<List<T>> w) {
        try {

            return gson.fromJson(jsonString, w.getType());
        } catch (Exception e) {
            Log.e("jsonString2List()", "异常:" + e.getMessage());
        }
        return null;
    }


    public static <T> String Bean2String(T t) {
        try {
            return gson.toJson(t);
        } catch (Exception e) {
            Log.e("jsonString2List()", "异常:" + e.getMessage());
        }
        return null;
    }

    public static <T> List<T> jsonString22List(String jsonString, TypeToken<List<T>> w) {
        try {

            return gson.fromJson(jsonString, w.getType());
        } catch (Exception e) {
            Log.e("jsonString2List()", "异常:" + e.getMessage());
        }
        return null;
    }
}
