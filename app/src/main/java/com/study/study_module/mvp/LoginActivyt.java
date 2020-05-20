package com.study.study_module.mvp;

import android.os.Bundle;

import com.study.study_module.mvp.presenter.MyLoginPresenter;

import androidx.annotation.Nullable;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 18:06
 *
 * @author syd
 * @version 1.0
 */
public class LoginActivyt extends BaseActivity<MyLoginPresenter> implements Contract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public void updateUI() {

    }

    @Override
    public void showError(String msg) {

    }
}
