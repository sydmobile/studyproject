package com.syd.study.okhttp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 说明：$d$
 * <p>
 * date: 2019/7/24 10:07
 *
 * @author syd
 * @version 1.0
 */
public class OkHttpUtil {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .build();

    private static final String CHARSET_NAME = "UTF-8";


    /**
     * 该方法不会开启异步线程
     *
     * @param request 请求报文
     * @return 响应报文对象
     * @throws IOException 异常处理
     */
    public static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }


    /**
     * 开启异步线程访问网络
     *
     * @param request          请求报文对象
     * @param responseCallBack 响应回调
     */
    public static void enqueue(Request request, Callback responseCallBack) {
        mOkHttpClient.newCall(request).enqueue(responseCallBack);
    }

    /**
     * 开启异步线程访问网络，不在乎返回结果
     *
     * @param request 请求报文对象
     */
    public static void enqueue(Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 获取响应体内容
     * @param url 请求 url
     * @return 响应体内容
     * @throws IOException 异常
     */
    public static String getStringFromServer(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code :" + response.code() + "message:"
                    + response.body().string());
        }
    }



}
