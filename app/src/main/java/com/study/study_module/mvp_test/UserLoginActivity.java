package com.study.study_module.mvp_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.study.R;
import com.study.base.BaseActivity;
import com.study.study_module.mvp_test.bean.User;
import com.study.study_module.mvp_test.presenter.UserLoginPresenter;
import com.study.study_module.mvp_test.view.IUserLoginView;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 9:49
 *
 * @author syd
 * @version 1.0
 */
public class UserLoginActivity extends BaseActivity implements IUserLoginView,
        View.OnClickListener {

    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_clear)
    Button btClear;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.pb)
    ProgressBar pb;
    UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_mvp_login;
    }
    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void initListener() {
        super.initListener();
        btLogin.setOnClickListener(this);
        btClear.setOnClickListener(this);

    }

    @Override
    public String getUserName() {

        return etUsername.getText().toString();
    }

    @Override
    public String getPassword() {

        return etPassword.getText().toString();
    }

    @Override
    public void showLoading() {

        pb.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() + "login success to MainActivity",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearUserName() {

        etUsername.setText("");


    }

    @Override
    public void clearPassword() {
        etPassword.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                userLoginPresenter.login();
                break;
            case R.id.bt_clear:
                userLoginPresenter.clear();
                break;

        }
    }
}
