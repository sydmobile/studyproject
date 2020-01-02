package com.syd.study.okhttp;

import android.os.Bundle;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

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
        setContentView(R.layout.activity_common);
        init();
    }

    @Override
    public void init() {
        super.init();
    }

    public void doget() {

    }

}
