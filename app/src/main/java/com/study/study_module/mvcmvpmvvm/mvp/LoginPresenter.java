package com.study.study_module.mvcmvpmvvm.mvp;
import com.study.study_module.mvcmvpmvvm.mvc.ILoginModel;
import com.study.study_module.mvcmvpmvvm.mvc.IView;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 16:31
 *
 * @author syd
 * @version 1.0
 */
public class LoginPresenter implements ILoginPresenter {

    IView iView;
    ILoginModel iLoginModel;

    public LoginPresenter(IView iView) {
        this.iView = iView;
        iLoginModel = new LoginModel(this);
    }

    @Override
    public void login(String name, String password) {
        if (name == null || name.trim().equals("")) {
            iView.loginFail("用户名不能为 null");
            return;
        }
        if (password == null || password.trim().equals("")) {
            iView.loginFail("密码不能为 null");
            return;
        }
        iLoginModel.login(name, password);
    }

    @Override
    public void result(String name) {
        // 这里就是可以进行一些逻辑判断，然后根据情况通知页面刷新
        if (name.equals("==")) {
            iView.loginFail(name);
        } else {
            iView.setLoginStatus(name);
        }
    }
}
