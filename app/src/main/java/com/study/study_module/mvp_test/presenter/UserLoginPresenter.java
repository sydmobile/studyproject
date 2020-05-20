package com.study.study_module.mvp_test.presenter;

import android.os.Handler;

import com.study.study_module.mvp_test.bean.User;
import com.study.study_module.mvp_test.biz.IUserBiz;
import com.study.study_module.mvp_test.biz.OnLoginListener;
import com.study.study_module.mvp_test.biz.UserBiz;
import com.study.study_module.mvp_test.view.IUserLoginView;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 9:50
 *
 * @author syd
 * @version 1.0
 */
public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(),
                new OnLoginListener() {
                    @Override
                    public void loginSuccess(User user) {
                        mHandler.post(() -> {
                            userLoginView.toMainActivity(user);
                            userLoginView.hideLoading();

                        });
                    }

                    @Override
                    public void loginFailed() {
                        mHandler.post(() -> {
                            userLoginView.showFailedError();
                            userLoginView.hideLoading();
                        });
                    }
                });
    }

    public void clear(){
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

}
