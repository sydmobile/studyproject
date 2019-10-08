package com.syd.study.mvp_test.biz;

import com.syd.study.mvp_test.bean.User;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 9:48
 *
 * @author syd
 * @version 1.0
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
