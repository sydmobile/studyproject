package com.syd.study.mvcmvpmvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.okhttp.OkHttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 11:45
 *
 * @author syd
 * @version 1.0
 */
public class LoginActivity extends BaseActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        super.initView();
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
                if (etUsername.getText() == null || etUsername.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名不能为 null", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etPassword.getText() == null || etPassword.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "密码不能为 null", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(etUsername.getText().toString(), etPassword.getText().toString());

            }
        });
    }

    /**
     * 登录业务，牵扯到数据
     * 真实情况下，有可能是 网络请求、本地数据库操作、大量数据逻辑操作
     * @param name
     * @param password
     */
    public void login(String name, String password) {
        FormBody formBody = new FormBody.Builder()
                .add("username", name)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url("http://192.168.1.120/login")
                .build();
        OkHttpUtil.enqueue(request, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // 处理一些业务逻辑
                tvStatus.setText("登录失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // 来更新页面内容
                tvStatus.setText("登录成功");
            }
        });
    }
}
