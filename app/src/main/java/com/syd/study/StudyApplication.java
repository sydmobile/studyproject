package com.syd.study;

import android.app.Application;

public class StudyApplication extends Application {

    private static StudyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static StudyApplication getAppInstance() {
        return myApplication;
    }
}
