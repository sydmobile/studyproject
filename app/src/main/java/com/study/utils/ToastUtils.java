package com.study.utils;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.study.R;
import com.study.StudyApplication;
import com.study.userful.util.StringUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 孙亦东 on 2016/8/16.
 */
@SuppressWarnings("unused")
public class ToastUtils {

    public static int LONG = Toast.LENGTH_LONG;
    public static int SHORT = Toast.LENGTH_SHORT;

    /**
     * 展示普通提示内容
     *
     * @param str      提示内容
     * @param gravity  位置
     * @param duration Set how long to show the view for.
     */
    public static void showCommonInfo(String str, int gravity, int duration) {
        View layout = View.inflate(StudyApplication.getAppInstance(), R.layout.toast_common, null);
        TextView tv = layout.findViewById(R.id.tv);
        if (!StringUtils.isEmptyAfterTrim(str)) {
            tv.setText(str);
        }
        Toast toast = new Toast(StudyApplication.getAppInstance());
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public static void showCommonInfo(String string,int gravity,int duration,int marginBottom,int textSize){
        View layout = View.inflate(StudyApplication.getAppInstance(),R.layout.toast_common,null);
        TextView tv = layout.findViewById(R.id.tv);
        tv.setTextSize(textSize);
        tv.setText(string);
        Toast toast = new Toast(StudyApplication.getAppInstance());
        float margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,marginBottom,StudyApplication.getAppInstance().getResources().getDisplayMetrics());
        toast.setGravity(gravity,0,(int)margin);
        toast.setView(layout);
        toast.setDuration(duration);
        toast.show();
    }


    /**
     * 展示自定义提示内容
     *
     * @param view     提示视图已经内容
     * @param gravity  位置
     * @param duration Set how long to show the view for.
     */
    public static void showCustomInfo(View view, int gravity, int duration) {
        Toast toast = new Toast(StudyApplication.getAppInstance());
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }


    public static void showCommonInfo(String content, int time) {
        View layout = View.inflate(StudyApplication.getAppInstance(), R.layout.toast_common, null);
        TextView tv = layout.findViewById(R.id.tv);
        tv.setText(content);
        final Toast toast = new Toast(StudyApplication.getAppInstance());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.setGravity(Gravity.BOTTOM, 0, 200);
                toast.show();
            }

        }, 0, 1);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                toast.cancel();

                timer.cancel();

            }

        }, time);

    }

}
