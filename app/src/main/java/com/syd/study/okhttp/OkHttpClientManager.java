package com.syd.study.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
    private Request buildPostRequest;

    // ClientManager 的私有构造方法
    private OkHttpClientManager() {
        mOkHttpClient = new OkHttpClient();
        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    /**
     * 获取 OkHttpClientManager 对象
     *
     * @return 实例
     */
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
     * @return Response
     * @throws IOException 异常
     */
    public static Response getSyn(String url) throws IOException {
        return getInstance()._getSyn(url);
    }

    /**
     * 同步 get 请求
     *
     * @param url url
     * @return String
     * @throws IOException 异常
     */
    public static String getSynString(String url) throws IOException {
        return getInstance()._getSynString(url);
    }

    /**
     * 异步 get 请求
     *
     * @param url      url
     * @param callback 回调
     */
    public static void getAsyn(String url, ResultCallback callback) {
        getInstance()._getAsyn(url, callback);
    }

    /**
     * 同步 post 请求
     *
     * @param url    url
     * @param params 参数
     * @return Response
     * @throws IOException 异常
     */
    public static Response postSyn(String url, Param... params) throws IOException {
        return getInstance()._post(url, params);
    }

    /**
     * 同步方法 post 请求
     *
     * @param url    url
     * @param params 参数
     * @return String
     * @throws IOException 异常
     */
    public static String postSynString(String url, Param... params) throws IOException {
        return getInstance()._postSynString(url, params);
    }

    public static void postAsyn(String url, ResultCallback callback, Param... params) {
        getInstance()._postAsyn(url, callback, params);
    }

    public static void postAsyn(String url, final ResultCallback callback,
                                Map<String, String> map) {
        getInstance()._postAsyn(url, callback, map);
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
     *
     * @param url url
     * @return 响应字符串
     * @throws IOException 异常
     */
    private String _getSynString(String url) throws IOException {

        Response response = _getSyn(url);
        return response.body().string();
    }

    // 向外部暴露的方法

    /**
     * 异步 get 请求
     *
     * @param url      url
     * @param callback callback
     */
    private void _getAsyn(String url, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }

    /**
     * 同步 post 请求
     *
     * @param url    url
     * @param params 参数
     * @return Response
     * @throws IOException 异常
     */
    private Response _post(String url, Param... params) throws IOException {
        Request request = buildPostRequest(url, params);
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 同步 post 请求获取 String
     *
     * @param url    url
     * @param params 参数
     * @return String
     * @throws IOException 异常
     */
    private String _postSynString(String url, Param... params) throws IOException {
        Response response = _post(url, params);
        return response.body().string();
    }

    /**
     * 异步 post 请求
     *
     * @param url      url
     * @param callback 回调
     * @param params   参数
     */
    private void _postAsyn(String url, final ResultCallback callback, Param... params) {
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步 post 请求
     *
     * @param url      url
     * @param callback 结果回调
     * @param params   参数
     */
    private void _postAsyn(String url, final ResultCallback callback, Map<String, String> params) {
        Param[] paramsArr = map2Params(params);
        Request request = buildPostRequest(url, paramsArr);
        deliveryResult(callback, request);
    }

    /**
     * 基于同步 post 上传文件
     *
     * @param url      url
     * @param files    文件
     * @param fileKeys 文件key
     * @param params   参数
     * @return Response
     * @throws IOException 异常
     */
    private Response _post(String url, File[] files, String[] fileKeys, Param... params) throws IOException {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        return mOkHttpClient.newCall(request).execute();
    }

    private Response _post(String url, File file, String fileKey) throws IOException {

        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey},
                null);
        return mOkHttpClient.newCall(request).execute();
    }

    private Response _post(String url, File file, String fileKey, Param... params) throws IOException {

        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey},
                params);
        return mOkHttpClient.newCall(request).execute();

    }

    /**
     * 基于异步的 post 上传文件
     */
    private void _postAsyn(String url, ResultCallback callback, File[] files, String[] fileKeys,
                           Param... params) throws IOException {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        deliveryResult(callback, request);
    }

    private void _postAsyn(String url, ResultCallback callback, File file, String fileKeys) {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKeys}
                , null);
        deliveryResult(callback, request);
    }

    private void _postAsyn(String url, ResultCallback callback, File file, String fileKey,
                           Param... params) {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey},
                params);
        deliveryResult(callback, request);
    }

    /**
     * 异步下载文件
     *
     * @param url        url
     * @param destFilDir 本地文件储存的文件夹
     * @param callback   callback
     */
    private void _downloadAsyn(final String url, final String destFilDir,
                               final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;
                try {
                    inputStream = response.body().byteStream();
                    File file = new File(destFilDir, getFileName(url));
                    fos = new FileOutputStream(file);
                    while ((len = inputStream.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    //  如果下载文件成功，第一个参数为文件的绝对路径
                    sendSuccessResultCallback(file.getAbsolutePath(), callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException ignored) {

                    }

                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException ignored) {

                    }

                }

            }
        });
    }


    private void setErrorResId(final ImageView view, final int errorResId) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                view.setImageResource(errorResId);
            }
        });
    }

    private String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1);
    }


    private Request buildMultipartFormRequest(String url, File[] files, String[] fileKeys,
                                              Param[] params) {
        params = validateParam(params);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (Param param : params) {
            builder.addPart(Headers.of("Content-Disposition", "form-data;name=\"" + param.key +
                            "\""),
                    RequestBody.create(null, param.value));
        }

        if (files != null) {
            RequestBody fileBody;
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data;name=\"" + fileKeys[i] + "\";filename" +
                                "=\"" + fileName + "\""),
                        fileBody);

            }
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    /**
     * 根据文件名来返回对应的 Content-Type
     *
     * @param fileName 文件名
     * @return Content_Type
     */
    private String guessMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(fileName);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private Param[] validateParam(Param[] params) {
        if (params == null) {
            return new Param[0];
        } else {
            return params;
        }
    }

    /**
     * @param params Map 参数
     * @return Param[]
     */
    private Param[] map2Params(Map<String, String> params) {
        if (params == null) {
            return new Param[0];
        }
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    private void deliveryResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String string = response.body().string();
                    if (callback.mType == String.class) {
                        sendSuccessResultCallback(string, callback);
                    } else {
                        Object o = mGson.fromJson(string, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } catch (Exception e) {
                    // json 解析错误
                    sendFailedStringCallback(response.request(), e, callback);
                }

            }
        });
    }

    private void sendFailedStringCallback(final Request request, final Exception e,
                                          final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(request, e);
                }
            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object);
                }
            }
        });

    }

    private Request buildPostRequest(String url, Param[] params) {
        if (params == null) {
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }


    public static abstract class ResultCallback<T> {
        Type mType;

        public ResultCallback() {
            mType = getSuperClassTypeParameter(getClass());
        }

        static Type getSuperClassTypeParameter(Class<?> subclass) {
            Type superClass = subclass.getGenericSuperclass();
            if (superClass instanceof Class) {
                throw new RuntimeException("missing type parameter");
            }
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }

        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response);

    }

    public static class Param {

        String key;
        String value;

        public Param() {

        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

    }


}
