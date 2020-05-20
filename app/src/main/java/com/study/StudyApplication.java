package com.study;

import android.app.Application;

import com.study.utils.SPUtils;

public class StudyApplication extends Application {

    private static StudyApplication myApplication;

    public static StudyApplication getAppInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        init();
    }

    public void init() {
        SPUtils.init();
    }
}
