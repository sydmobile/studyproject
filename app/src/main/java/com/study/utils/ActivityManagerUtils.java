package com.study.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * 说明：用于管理 Activity
 * <p>
 * date: 2020/4/22 10:03
 *
 * @author syd
 * @version 1.0
 */
public class ActivityManagerUtils {

    private static ActivityManagerUtils instance = new ActivityManagerUtils();
    // activity 栈
    private Stack<Activity> activities = new Stack<>();


    private ActivityManagerUtils() {

    }

    public static ActivityManagerUtils getInstance() {
        return instance;
    }

    /**
     * 进栈
     *
     * @param activity 当前 Activity
     */
    public void pushOneActivity(Activity activity) {
        if (activities != null) {

            activities.add(activity);
        }
    }

    /**
     * 出栈
     */
    public void popOneActivity() {
        if (activities != null && activities.size() > 0) {
            activities.pop();
        }
    }

    /**
     * 关闭特定的 Activity
     *
     * @param cls cls
     */
    public void finishActivity(Class cls) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(cls)) {
                finish(activity);
            }
        }
    }

    /**
     * 结束所有 Activity
     */
    public void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }

    private void finish(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
    }
}
