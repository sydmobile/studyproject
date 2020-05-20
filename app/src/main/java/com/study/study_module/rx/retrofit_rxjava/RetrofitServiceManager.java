package com.study.study_module.rx.retrofit_rxjava;

import com.study.study_module.retrofit.HttpCommonInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 说明：RetrofitServiceManager
 * <p>
 * date: 2020/4/17 13:59
 *
 * @author syd
 * @version 1.0
 */
public class RetrofitServiceManager {

    // 超时时间
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private static final String BASE_URL = "https://wanandroid.com/";
    private Retrofit mRetrofit;
    private RetrofitServiceManager(){
        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("userToken","123123")
                .build();

        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 连接超时时间
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                // 写操作 超时时间
        .writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS)
                // 读操作 超时时间
        .readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
//        .addInterceptor(commonInterceptor);
        // 创建 Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的 Service
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
