package com.study.utils.okhttp;

import android.util.Log;

import com.study.config.App;
import com.study.userful.util.L;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 说明：打印 logging拦截器
 * <p>
 * date: 2020/5/12 15:39
 *
 * @author syd
 * @version 1.0
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (App.IS_LOG_OK_HTTP) {

            L.e(App.LOG_TAG_OK_HTTP_RQ, String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
        }

        Response response = chain.proceed(request);

        if (App.IS_LOG_OK_HTTP) {

            Log.e(App.LOG_TAG_OK_HTTP_RSP, String.format("Received response for %s in %n%s",
                    response.request().url(), response.headers()));
        }
        return response;
    }
}
