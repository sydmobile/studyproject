package com.study.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.study.userful.util.L;
import com.study.utils.ActivityManagerUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * 说明：Activity 基础类
 * <p>
 * date: 2019/5/10 17:07
 *
 * @author syd
 * @version 1.0
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    public String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        // 入栈
        ActivityManagerUtils.getInstance().pushOneActivity(this);
        TAG = this.getClass().getSimpleName();
        init();
    }

    protected abstract int layoutId();

    public void init() {
        L.e("init");
        initData();
        initView();
        initListener();
        initNetData();
    }

    public void initData() {
        L.e("initData");
    }

    public void initView() {
        L.e("iniView");
    }

    public void initListener() {
        L.e("initListener");
    }

    public void initNetData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从栈中移除
        ActivityManagerUtils.getInstance().popOneActivity();
    }

    /**
     * Toast 功能
     *
     * @param content 要显示的内容
     */
    public void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

}
