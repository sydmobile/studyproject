package com.study.study_module.mvc.model;

import com.study.study_module.mvp_test.biz.OnLoginListener;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 15:04
 *
 * @author syd
 * @version 1.0
 */
public interface IUserBiz {
    void login(String username, String password, OnLoginListener onLoginListener);
}
