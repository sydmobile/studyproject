package com.study.study_module.mvp.google;

/**
 * 说明：BaseView
 * <p>
 * date: 2020/4/14 14:31
 *
 * @author syd
 * @version 1.0
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}
