package com.syd.study.mvp_test.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.mvp_test.presenter.CidianPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 16:52
 *
 * @author syd
 * @version 1.0
 */
public class MVCActivity extends BaseActivity implements IfanyiView, View.OnClickListener {

    CidianPresenter cidianPresenter;//声明了 Presenter 对应类
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_clear)
    Button btClear;
    @BindView(R.id.pb)
    ProgressBar pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void init() {
        super.init();
        cidianPresenter = new CidianPresenter(this);
    }

    @Override
    public void initListener() {
        super.initListener();
        btLogin.setOnClickListener(this);
    }

    @Override
    public void setInfo(String str) {
        tvUsername.setText(str);
    }

    @Override
    public void setError() {
        tvUsername.setText("查询不成功，请检查网络");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                if (cidianPresenter!=null){
                    cidianPresenter.inputToModel(etUsername.getText().toString(),MVCActivity.this);
                    List list = new ArrayList();
                }
                break;
        }
    }

}


