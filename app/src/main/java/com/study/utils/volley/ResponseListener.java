package com.study.utils.volley;

import android.view.Gravity;

import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.study.config.App;
import com.study.utils.ToastUtils;

/**
 * 说明：volley 请求结果处理类
 * <p>
 * date: 2020/5/2 17:00
 *
 * @author syd
 * @version 1.0
 */
@SuppressWarnings("WeakerAccess")
public abstract class ResponseListener {

    /**
     * 处理网络请求结果
     *
     * @param main  响应内容
     * @param error 异常
     */
    protected abstract void onResponse(String main, Exception error);

    /**
     * 处理异常
     *
     * @param volleyError 异常
     */
    public void handleVolleyError(VolleyError volleyError) {
        if (volleyError != null) {
            // 网络连接超时
            if (volleyError instanceof TimeoutError) {
                ToastUtils.showCommonInfo(App.APP_CONSTANT.TIME_OUT, Gravity.BOTTOM,
                        ToastUtils.SHORT);
                return;
            }
            if (volleyError instanceof ServerError) {
                if (volleyError instanceof ClientError) {
                    ToastUtils.showCommonInfo(App.APP_CONSTANT.CLIENT_ERROR, Gravity.BOTTOM
                            , ToastUtils.SHORT);
                    return;
                }
                ToastUtils.showCommonInfo(App.APP_CONSTANT.SERVER_ERROR, Gravity.BOTTOM,
                        ToastUtils.SHORT);
                return;
            }
            if (volleyError instanceof ParseError) {
                ToastUtils.showCommonInfo(App.APP_CONSTANT.PARSE_ERROR, Gravity.BOTTOM,
                        ToastUtils.SHORT);
                return;
            }
            // Socket 关闭，服务器宕机，DNS 错误都会产生，最大可能是服务器宕机
            if (volleyError instanceof NetworkError) {
                ToastUtils.showCommonInfo(App.APP_CONSTANT.NETWORK_ERROR, Gravity.BOTTOM,
                        ToastUtils.SHORT);
                return;
            }

            ToastUtils.showCommonInfo(volleyError.getMessage(), Gravity.BOTTOM, ToastUtils.SHORT);

        }
    }


}
