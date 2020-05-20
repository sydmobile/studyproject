package com.study.study_module.mvcmvpmvvm.mvp;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 16:37
 *
 * @author syd
 * @version 1.0
 */
public interface ILoginPresenter {
    void login(String name,String password);
    void result(String name);
}
