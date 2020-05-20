package com.study.study_module.mvcmvpmvvm.mvc;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 14:07
 *
 * @author syd
 * @version 1.0
 */
public interface IView {
     void setLoginStatus(String loginStatus);

     void loginFail(String fail);

     void error(String error);
}
