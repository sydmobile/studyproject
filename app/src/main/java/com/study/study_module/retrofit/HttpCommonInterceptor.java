package com.study.study_module.retrofit;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 说明：拦截器
 * <p>
 * 向请求头中添加公共参数
 * date: 2020/1/8 12:06
 *
 * @author syd
 * @version 1.0
 */
public class HttpCommonInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    private HttpCommonInterceptor() {

    }

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 添加新的参数到 url 中
//        HttpUrl.Builder builder = oldRequest.url().newBuilder()
//                .scheme()
//                .host()
//                .addQueryParameter();
        // 新的请求
        Request.Builder requestBuilder = oldRequest.newBuilder();


        requestBuilder.method(oldRequest.method(), oldRequest.body());
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {

        HttpCommonInterceptor mHttpCommonInterceptor;

        public Builder(){
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public HttpCommonInterceptor build() {
            return mHttpCommonInterceptor;

        }

    }
}
