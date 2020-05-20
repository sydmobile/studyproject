package com.study.config;

/**
 * 说明：全局配置内容
 * <p>
 * date: 2019/5/10 17:56
 *
 * @author syd
 * @version 1.0
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
public interface App {

    // 是否处于 debug 状态：决定了 log toast
    public static final boolean DEBUG = true;

    // 是否打印 okHttp 请求内容
    public static final boolean IS_LOG_OK_HTTP = true;

    // log 的默认标志
    public static final String LOG_TAG = "Test:";
    // 网络请求打印标志
    public static final String LOG_TAG_REQUEST = "REQUEST";
    // okHttp 网络请求打印标志
    public static final String LOG_TAG_OK_HTTP = "OK_HTTP";
    public static final String LOG_TAG_OK_HTTP_RQ = "OK_HTTP_RQ";
    public static final String LOG_TAG_OK_HTTP_RSP = "OK_HTTP_RSP";
    // 下载进度
    public static final String APK_DOWN = "APK_DOWN";

    @SuppressWarnings("unused")
    interface APP_CONSTANT {
        // 关于网络的提示语
        String TIME_OUT = "网络请求超时，请重试！";
        String SERVER_ERROR = "服务器异常";
        String CLIENT_ERROR = "参数有误";
        String NETWORK_ERROR = "请检查网络";
        String PARSE_ERROR = "数据解析错误";

    }

}
