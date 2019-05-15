package com.syd.study.util

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * 说明：$d$
 * <p>
 * date: 2019/5/10 17:39
 * @author syd
 * @version 1.0
 */

object ActivityCollector {
    private const val TAG = "ActivityCollector"

    private val activityList = ArrayList<WeakReference<Activity>?>()

    fun size(): Int {
        return activityList.size
    }

    fun add(weakReference: WeakReference<Activity>?) {
        activityList.add(weakReference)
    }


}