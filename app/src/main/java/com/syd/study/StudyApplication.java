package com.syd.study;

import android.app.Application;

public class StudyApplication extends Application {

    private static StudyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static StudyApplication getAppInstance() {
        return myApplication;
    }
}
