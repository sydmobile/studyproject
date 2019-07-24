package com.syd.study.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 说明：okHttp 封装
 * <p>
 * date: 2019/7/24 16:23
 *
 * @author syd
 * @version 1.0
 */
public class OkHttpClientManager {
    private static OkHttpClientManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Gson mGson;
    private static final String TAG = "OkHttpClientManager";
    private OkHttpClientManager() {

        mOkHttpClient = new OkHttpClient();
        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    public static OkHttpClientManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }

            }
        }
        return mInstance;
    }

    /**
     * 同步 get 请求
     *
     * @param url url
     * @return 响应报文
     * @throws IOException 异常
     */
    private Response _getSyn(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();

        return mOkHttpClient.newCall(request)
                .execute();

    }

    /**
     * 同步 get 请求获取字符串
     * @param url url
     * @return 响应字符串
     * @throws IOException 异常
     */
    private String _getSynString(String url) throws IOException{

        Response response = _getSyn(url);
        return response.body().string();
    }




}
