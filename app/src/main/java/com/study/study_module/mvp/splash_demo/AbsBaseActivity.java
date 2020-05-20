package com.study.study_module.mvp.splash_demo;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 说明：$
 * <p>
 * date: 2020/4/14 16:16
 *
 * @author syd
 * @version 1.0
 */
@SuppressLint("Registered")
public class AbsBaseActivity extends AppCompatActivity implements BaseView {


    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void close() {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void showProgress(String msg, int progress) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMessage(String msg, String content) {

    }
}
