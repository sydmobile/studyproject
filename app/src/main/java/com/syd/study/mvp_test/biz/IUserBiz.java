package com.syd.study.mvp_test.biz;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 9:45
 *
 * @author syd
 * @version 1.0
 */
public interface IUserBiz {

    public void login(String username,String password,OnLoginListener loginListener);
}
