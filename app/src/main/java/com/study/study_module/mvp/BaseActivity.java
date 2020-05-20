package com.study.study_module.mvp;

import android.annotation.SuppressLint;
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
@SuppressLint("Registered")
public class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

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
