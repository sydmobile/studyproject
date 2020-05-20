package com.study.utils.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.study.StudyApplication;
import com.study.config.App;
import com.study.userful.util.L;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 说明：Volley 请求 封装
 * <p>
 * date: 2020/5/2 16:17
 *
 * @author syd
 * @version 1.0
 */
@SuppressWarnings("unused")
public class HttpRequest {
    private RequestQueue mQueue;
    // 单例
    private static HttpRequest httpRequest;
    // 统一的请求头 可以在构造函数中统一添加内容
    private Parameter headers = new Parameter();
    // 统一的请求参数 可以在构造函数中统一添加内容
    private Parameter parameters = new Parameter();

    private HttpRequest() {

    }

    /**
     * 获取 请求对象
     *
     * @return HttpRequest
     */
    public static HttpRequest getInstance() {
        if (httpRequest == null) {
            synchronized (HttpRequest.class) {
                if (httpRequest == null) {
                    httpRequest = new HttpRequest();
                    httpRequest.mQueue = Volley.newRequestQueue(StudyApplication.getAppInstance());
                }
            }
        }
        return httpRequest;
    }

    /**
     * 请将SSL证书文件放在assets目录中，例如“ssl.crt”；
     * 以附带SSL证书名的方式创建请求：
     * HttpRequest.getInstance("ssl.crt")
     *
     * @param SSLFileNameInAssets SSL证书文件名
     * @return HttpRequest
     */
    public static HttpRequest getInstance(String SSLFileNameInAssets) {
        if (httpRequest == null) {
            synchronized (HttpRequest.class) {
                SSLSocketFactory sslSocketFactory =
                        initSSLSocketFactory(StudyApplication.getAppInstance(),
                                SSLFileNameInAssets);
                HurlStack stack = new HurlStack(null, sslSocketFactory);
                httpRequest.mQueue = Volley.newRequestQueue(StudyApplication.getAppInstance(),
                        stack);
            }
        }
        return httpRequest;
    }

    //生成SSLSocketFactory
    private static SSLSocketFactory initSSLSocketFactory(Context context,
                                                         String SSLFileNameInAssets) {
        //生成证书:Certificate
        CertificateFactory cf;
        SSLSocketFactory factory = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = context.getAssets().open(SSLFileNameInAssets);
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                try {
                    caInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //初始化公钥:keyStore
            String keyType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            //初始化TrustManagerFactory
            String algorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory managerFactory = TrustManagerFactory.getInstance(algorithm);
            managerFactory.init(keyStore);

            //初始化sslContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, managerFactory.getTrustManagers(), null);
            factory = sslContext.getSocketFactory();

        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException | KeyManagementException e) {
            e.printStackTrace();
        }

        return factory;
    }

    /**
     * 发起 post 请求
     *
     * @param partUrl   请求地址
     * @param parameter 请求参数
     * @param headers   请求头
     * @param listener  监听
     */
    public void postRequest(@NonNull String partUrl, @Nullable final Parameter parameter,
                            @Nullable Parameter headers,
                            @NonNull final ResponseListener listener) {
        doRequest(partUrl, parameter, headers, listener, Request.Method.POST);
    }

    /**
     * 发起 get 请求
     *
     * @param partUrl   请求地址
     * @param parameter 请求参数
     * @param headers   请求头
     * @param listener  监听
     */
    public void getRequest(@NonNull String partUrl, @Nullable final Parameter parameter,
                           @Nullable Parameter headers,
                           @NonNull final ResponseListener listener) {
        doRequest(partUrl, parameter, headers, listener, Request.Method.GET);
    }

    /**
     * 真正执行方法
     */
    private void doRequest(String partUrl, final Parameter parameter, Parameter headers,
                           final ResponseListener listener, final int method) {

        final String finalUrl = partUrl;

        BaseRequest baseRequest = new BaseRequest(method, parameter, headers, finalUrl,
                response -> {
                    try {
                        L.e(App.LOG_TAG_REQUEST,
                                "request:" + finalUrl
                                        + "\nparameter:" + (parameter == null ? "" :
                                        parameter.toParameterString())
                                        + "\nresponse:" + response);
                        listener.onResponse(response, null);
                    } catch (Exception e) {
                        listener.onResponse(null, e);
                        e.printStackTrace();
                    }
                }, error -> {
            error.printStackTrace();
            listener.handleVolleyError(error);
        });
        mQueue.add(baseRequest);
    }

    /**
     * Request对象
     */
    class BaseRequest extends StringRequest {

        private Parameter params;
        private Parameter headers;


        BaseRequest(int method, Parameter params, Parameter headers, String url,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
            this.params = params;
            this.headers = headers;
        }

        @Override
        protected Map<String, String> getParams() {
            if (params == null) {
                return HttpRequest.this.parameters;
            } else {
                params.putAll(HttpRequest.this.parameters);
                return params;
            }
        }

        @Override
        public Map<String, String> getHeaders() {
            if (headers == null) {
                return HttpRequest.this.headers;
            } else {
                headers.putAll(HttpRequest.this.headers);
                return headers;
            }
        }
    }

}
