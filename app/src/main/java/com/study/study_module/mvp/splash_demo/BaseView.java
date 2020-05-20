package com.study.study_module.mvp.splash_demo;

/**
 * 说明：BaseView
 * <p>
 * date: 2020/4/14 16:02
 *
 * @author syd
 * @version 1.0
 */
public interface BaseView {

    void showMessage(String msg);

    void close();

    void showProgress(String msg);

    void showProgress(String msg,int progress);

    void hideProgress();

    void showErrorMessage(String msg,String content);

}
