package com.syd.study.mvcmvpmvvm.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.mvcmvpmvvm.mvc.ILoginController;
import com.syd.study.mvcmvpmvvm.mvc.IView;
import com.syd.study.mvcmvpmvvm.mvc.LoginController;
import com.syd.study.mvcmvpmvvm.mvc.LoginModel;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 16:46
 *
 * @author syd
 * @version 1.0
 */
public class LoginMVPActivity extends BaseActivity implements IView {


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
    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        super.initView();
        loginPresenter = new LoginPresenter(this);
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
                loginPresenter.login(etUsername.getText().toString(),
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
