package com.syd.study.mvc.model;

import com.syd.study.mvp_test.biz.OnLoginListener;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 15:04
 *
 * @author syd
 * @version 1.0
 */
public interface IUserBiz {
    public void login(String username, String password, OnLoginListener onLoginListener);
}
