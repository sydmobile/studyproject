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
public class UserBiz implements IUserBiz {
    @Override
    public void login(String username, String password, OnLoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("syd".equals(username)&&"123".equals(password)){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    if (loginListener!=null){
                        loginListener.loginSuccess(user);
                    }
                }else {
                    loginListener.loginFailed();
                }
            }
        }).start();
    }
}
