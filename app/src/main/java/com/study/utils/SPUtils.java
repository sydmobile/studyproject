package com.study.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.study.StudyApplication;

/**
 * 说明：使用前需要先 init
 * <p>
 * date: 2020/4/22 16:58
 *
 * @author syd
 * @version 1.0
 */
public class SPUtils {

    private static final String FILE_NAME = StudyApplication.getAppInstance().getPackageName();
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    /**
     * 初始化
     */
    @SuppressLint("CommitPrefEdits")
    public static void init() {
        sp = StudyApplication.getAppInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * put 并提交
     *
     * @param key    key
     * @param object 要保存的数据
     */
    public static void putAndApply(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 只是 put ，可以多次 put 后统一提交
     * 注意只是 put 没有提交
     *
     * @param key    key
     * @param object 数据
     */
    public static void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
    }

    public static void apply() {
        editor.apply();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           key
     * @param defaultObject 默认值
     * @return 保存数据
     */
    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }


    /**
     * 移除某个key值已经对应的值
     * @param key     关键字
     */
    public static void remove(String key) {

        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     *
     */
    public static void clear() {
        editor.clear();
        editor.apply();
    }
}
