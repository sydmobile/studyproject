package com.study.study_module.mvcmvpmvvm.mvc;


/**
 * 说明：$
 * <p>
 * date: 2019/11/29 15:18
 *
 * @author syd
 * @version 1.0
 */
public class LoginController implements ILoginController{
    private IView iView;
    private ILoginModel iLoginModel;

    public LoginController(IView iView,ILoginModel iLoginModel){
        this.iView = iView;
        this.iLoginModel = iLoginModel;
    }

    @Override
    public void login(String name, String password) {
        if (name== null || name.trim().equals("")) {
            iView.loginFail("用户名不能为 null");
            return;
        }
        if (password == null || password.trim().equals("")) {
            iView.loginFail("密码不能为 null");
            return;
        }
        iLoginModel.login(name, password);
    }
}
