package com.study.study_module.mvp.presenter;

import com.study.study_module.mvp.Contract;
import com.study.study_module.mvp.Contract.Presenter;

/**
 * 说明:
 * <p>
 * date: 2019/11/29 17:30
 *
 * @author syd
 * @version 1.0
 */
public class MyLoginPresenter extends Presenter {
    @Override
    public void login(String username, String password) {
        model.login(username, password, new Contract.Callback() {
            @Override
            public void onResult(String text) {
                view.updateUI();
            }
        });
    }

    @Override
    public Contract.IModel createModel() {

        return new MyLoginModel();
    }
}
