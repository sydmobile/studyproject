package com.syd.study.mvp_test.view;

import com.syd.study.mvp_test.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 9:49
 *
 * @author syd
 * @version 1.0
 */
public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

    void clearUserName();

    void clearPassword();
}
