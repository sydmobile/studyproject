package com.study.study_module.okhttp;

import android.os.Bundle;

import com.study.R;
import com.study.base.BaseActivity;

import androidx.annotation.Nullable;
import okhttp3.OkHttpClient;

/**
 * 说明：$
 * <p>
 * date: 2019/12/24 18:13
 *
 * @author syd
 * @version 1.0
 */
public class OkHttpTest extends BaseActivity {
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_common;
    }


    @Override
    public void init() {
        super.init();
    }

    public void doget() {

    }

}
