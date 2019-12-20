package com.syd.study.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 16:07
 *
 * @author syd
 * @version 1.0
 */
public  class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }

    }
}
