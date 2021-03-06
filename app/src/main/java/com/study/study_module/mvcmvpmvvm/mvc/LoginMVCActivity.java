package com.study.study_module.mvcmvpmvvm.mvc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.study.R;
import com.study.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 14:04
 *
 * @author syd
 * @version 1.0
 */
public class LoginMVCActivity extends BaseActivity implements IView {
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
    @BindView(R.id.tv_status)
    TextView tvStatus;
    LoginModel loginModel;
    ILoginController loginController;

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
        loginModel = new LoginModel(this);
        loginController = new LoginController(this, loginModel);
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPassword.setText("");
                etUsername.setText("");

            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginController.login(etUsername.getText().toString(),
                        etPassword.getText().toString());

            }
        });

    }

    @Override
    public void setLoginStatus(String loginStatus) {
        tvUsername.setText(loginStatus);
    }

    @Override
    public void loginFail(String fail) {
        tvUsername.setText(fail);
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
