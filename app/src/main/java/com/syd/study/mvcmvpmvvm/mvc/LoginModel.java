package com.syd.study.mvcmvpmvvm.mvc;

import com.syd.study.okhttp.OkHttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 14:05
 *
 * @author syd
 * @version 1.0
 */
public class LoginModel implements ILoginModel{
    private IView iView;
    public LoginModel(IView iView){
        this.iView = iView;
    }


    public void login(String name,String password){
        FormBody formBody = new FormBody.Builder()
                .add("username",name)
                .add("password",password)
                .build();
        Request request = new  Request.Builder()
                .post(formBody)
                .url("http://192.168.1.120/login")
                .build();
        OkHttpUtil.enqueue(request, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // 登录失败了，真实情况下可能会经过一些逻辑判断，然后通知页面发生更新
                //..... 等等一些逻辑处理
                iView.loginFail("");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //..... 等等一些逻辑处理
                // 来更新页面内容
                iView.setLoginStatus("登录成功");
            }
        });
    }

}
