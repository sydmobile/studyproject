package com.syd.study.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 说明：自定义 Log 工具类
 *
 * @author sunyidong
 * @version 1.0
 * 创建时间 2018/9/3 13:50
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class L {
    // 是否进行 Log 的标志，true 进行 log,
    private static boolean mDebug = true;
    private static String mTag = "msyt:";
    // 堆栈跟踪
    private static StackTraceElement stackTraceElement;

    /**
     * 初始化
     *
     * @param debug 是否是 debug 状态
     * @param tag   打印标志
     */
    public static void init(boolean debug, String tag) {
        mDebug = debug;
        mTag = tag;
    }

    /**
     * 更新 mDebug 状态
     */
    public static void updateMDebug() {
        mDebug = !mDebug;
    }

    /**
     * 普通打印模式
     *
     * @param msg message
     */
    public static void e(String msg) {
        e("", msg);
    }

    /**
     * 普通打印
     *
     * @param tag tag
     * @param msg msg
     */
    public static void e(String tag, String msg) {
        if (mDebug)
            Log.e(mTag + tag, msg);
    }

    /**
     * 专门用于测试使用
     * @param tag tag
     * @param msg 信息
     */
    public static void e_test(String tag, String msg) {
        mDebug = false;
        Log.e(msg + "test:" + tag, msg);
    }

    /**
     * 打印详情
     *
     * @param msg    msg
     * @param params 参数
     */
    public static void e_detail(String msg, Object... params) {
        e_detail("", msg, params);

    }

    /**
     * 自定义 Log.e 方法
     *
     * @param tag    标志
     * @param msg    要打印的信息
     * @param params msg 中的变量
     */
    public static void e_detail(String tag, String msg, Object... params) {
        if (!mDebug)
            return;
        stackTraceElement = getTargetStackTranceElement();
        Log.e(getFinalTag(". " + tag), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Log.e(getFinalTag(" ." + tag), "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")");
        Log.e(getFinalTag("  " + tag), "Method:" + stackTraceElement.getMethodName());
        Log.e(getFinalTag(". " + tag), String.format(msg, params));
        Log.e(getFinalTag(" ." + tag), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }


    /**
     * 打印 Json 字符串
     * 将 Json 串有格式的输出
     *
     * @param tag  标签
     * @param json json 串
     */
    public static void json(String tag, String json) {
        if (!mDebug)
            return;
        stackTraceElement = getTargetStackTranceElement();
        Log.e(getFinalTag(tag), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Log.e(getFinalTag(tag), "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")");
        Log.e(getFinalTag(tag), "Method:" + stackTraceElement.getMethodName());
        Log.e(getFinalTag(tag), getJson(json));
        Log.e(getFinalTag(tag), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    public static void json(String json) {
        json(null, json);
    }

    /**
     * 将 json 串进行格式化后返回
     *
     * @param json json 串
     * @return json 串
     */
    public static String getJson(String json) {
        try {
            //取出不必要的空格
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                return jsonObject.toString(6);
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                return jsonArray.toString(6);
            }
        } catch (Exception e) {
            return json;
        }
        return "Invalid Json,Please Check:" + json;

    }

    /**
     * 获取最终的 tag
     *
     * @param tag tag
     * @return 最终 tag
     */
    private static String getFinalTag(String tag) {
        if (!StringUtils.isEmptyAfterTrim(tag)) {
            return mTag + tag;
        } else {
            return mTag;
        }
    }

    /**
     * 获取 堆栈跟踪
     * 根据这个对象获取打印的行数和方法
     *
     * @return 堆栈跟踪对象
     */
    private static StackTraceElement getTargetStackTranceElement() {
        boolean shouldTrace = false;
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(L.class.getName());
            if (shouldTrace && !isLogMethod) {
                L.stackTraceElement = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return L.stackTraceElement;

    }

}
