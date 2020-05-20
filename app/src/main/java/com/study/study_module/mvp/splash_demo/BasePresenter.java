package com.study.study_module.mvp.splash_demo;

/**
 * 说明：BasePresenter
 * <p>
 * date: 2020/4/14 16:08
 *
 * @author syd
 * @version 1.0
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
