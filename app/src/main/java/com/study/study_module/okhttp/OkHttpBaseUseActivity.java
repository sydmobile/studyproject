package com.study.study_module.okhttp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.RecoverySystem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;
import com.study.config.AppConfig;
import com.study.userful.util.L;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import butterknife.BindView;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import static com.study.config.App.IS_LOG_OK_HTTP;

/**
 * 说明： ok http 基础使用
 * <p>
 * date: 2019/7/23 16:57
 *
 * @author syd
 * @version 1.0
 */
@SuppressWarnings("unused")
public class OkHttpBaseUseActivity extends BaseActivity implements View.OnClickListener {

    private OkHttpClient mOkHttpClient = new OkHttpClient();
    @BindView(R.id.bt_get)
    Button btGet;
    @BindView(R.id.bt_post)
    Button btPost;
    @BindView(R.id.bt_json)
    Button btJson;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bt_get_asyn)
    Button btGetAsyn;
    @BindView(R.id.bt_post_asyn)
    Button btPostAsyn;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.bt_app_interceptor)
    Button btAppInterceptor;
    @BindView(R.id.bt_net_interceptor)
    Button btNetInterceptor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_okhttp_base_use;
    }

    @Override
    public void initListener() {
        super.initListener();
        btGet.setOnClickListener(this);
        btGetAsyn.setOnClickListener(this);
        btPost.setOnClickListener(this);
        btPostAsyn.setOnClickListener(this);
        btJson.setOnClickListener(this);
        btUpload.setOnClickListener(this);
        btNetInterceptor.setOnClickListener(this);
        btAppInterceptor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_get:
                goGet();
                break;
            case R.id.bt_get_asyn:
                goAsyGet();
                break;
            case R.id.bt_post:
                goPost();
                break;
            case R.id.bt_post_asyn:
                goPostAsyn();
                break;
            case R.id.bt_json:
                jsonPost();
                break;
            case R.id.bt_upload:
                upLoadFile();
                break;
            case R.id.bt_app_interceptor:
                applicationInterceptor();
                break;
            case R.id.bt_net_interceptor:
                netInterceptor();
                break;
        }

    }


    /**
     * get 同步请求
     */
    public void goGet() {
        new Thread(() -> {
            // 创建请求客户端
            OkHttpClient client = new OkHttpClient();
            // 构建请求报文
            Request request = new Request.Builder()
                    .url("http://192.168.1.100:8100/firew/uav/readyInfo")
                    .build();
            // 同步执行
            Response response;
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        runOnUiThread(() -> {
                            try {
                                tvContent.setText(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    // .body 获取响应体的内容

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

    /**
     * get 异步请求
     */
    public void goAsyGet() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .cache(new Cache(new File("xx"),1024))
                .build();

        Request request = new Request.Builder()
                .url(AppConfig.URL.url_get)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // 没有在 ui 线程中
                runOnUiThread(() -> {
                    e.printStackTrace();
                    tvContent.setText(e.getMessage());
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                String content = null;
                try {
                    content = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String finalContent = content;
                runOnUiThread(() -> {
                    try {
                        tvContent.setText(finalContent);
                    } catch (Exception e) {
                        L.e(e.getMessage());
                        e.printStackTrace();
                    }
                });
            }
        });
    }


    /**
     * post 同步请求
     */
    @SuppressLint("SetTextI18n")
    public void goPost() {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            // 可以通过 .post .put .get 快速设置请求方式。也可以通过 .method 自己定义
            // 默认 get 请求 （只有 .url 的时候）
            FormBody formBody = new FormBody.Builder()
                    .add("hospitalId", "6")
                    .build();
            // 可以
            Request request = new Request.Builder()
                    .url(AppConfig.URL.post_request)
                    .post(formBody)
                    .build();
            // request  的方法， body() 返回请求体  cacheControl() 缓存有关
            // header(String name) 获取某个请求头的内容
            // headers() 返回 Header 请求头
            // isHttps() 判断是否是 Https 请求
            try {
                // 响应数据包，关于响应的信息都可以获取
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        try {
                            assert response.body() != null;
                            tvContent.setText("同步 post 请求：" + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

    /**
     * 普通 post 异步请求
     */
    public void goPostAsyn() {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("hospitalId", "6")
                .build();
        Request request = new Request.Builder()
                .url(AppConfig.URL.post_request)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(() -> tvContent.setText("失败：" + e.getMessage()));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                runOnUiThread(() -> {
                    try {
                        assert response.body() != null;
                        tvContent.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    /**
     * json 请求
     */
    public void jsonPost() {
        new Thread(() -> {

            OkHttpClient okHttpClient = new OkHttpClient();
            MediaType json = MediaType.parse("application/json; charset=utf-8");
            String jsonString = "{\n" +
                    "\t\"phoneBrand\":\"xiaomi\",\n" +
                    "\t\"phoneModel\":\"mix2\",\n" +
                    "\t\"description\":\"随便测试\",\n" +
                    "\t\"ble\":\"1562541552233:12,-55\",\n" +
                    "\t\"angle\":\"1562541552233:33\",\n" +
                    "\t\"step\":\"1562541552233:1\"\n" +
                    "}";
            RequestBody requestBody = RequestBody.create(json, jsonString);
            Request request = new Request.Builder().url(AppConfig.URL.json_request)
                    .post(requestBody)
                    .build();

            try {
                Response response = okHttpClient.newCall(request).execute();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    L.e(response.body().string());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    // http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html

    // 下载一个文件，打印它的响应头，以 String 形式打印响应体。
    // 响应体的 string() 方法对于小文档来说十分方便、高效。但是如果响应体太大（超过 1M），应
    // 避免使用 string() 方法，因为他会将整个文档的内容加载到内存中。对于超过 1M 的响应 body,应使用流的方式来处理 body


    /*
      典型的 HTTP 头 像是一个 Map<String,String> 每个字段都有一个或者没有值。但是有的 头允许有多个值。
      列入：HTTP 响应中的 Vary 响应头，就是多个值。
      当写请求头的时候，使用 header(name,value) 可以设置唯一的 name，value 。如果已经有了则旧的值被覆盖。
      使用 header(name) 返回最后出现的 name 、value 。通常情况这种是唯一的 name、value。如果没有值那么
      header(name) 返回 null。如果想读取字段所对应的所有值，使用 headers(name) 会返回一个 list。
     */


    /**
     * post 方式提交 String
     */
    public void submitString() {
        MediaType MARKDOWN = MediaType.parse("text/x-markdown;charset=UTF-8");
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        Request request = new Request.Builder()
                .url(AppConfig.URL.post_text)
                .post(RequestBody.create(MARKDOWN, postBody))
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(() -> tvContent.setText(String.format("失败：%s", e.getMessage())));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                runOnUiThread(() -> {
                    try {
                        assert response.body() != null;
                        tvContent.setText(String.format("成功：%s", response.body().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        });
    }

    /**
     * 以流的方式 POST 提交请求体。请求体的内容由流写入产生。
     * 例子：流直接写入 Okio 的 BufferedSink 如果使用 OutputStream 的时候
     * 可以使用 BufferSink.outputStream 来获取
     */
    @SuppressWarnings("unused")
    public void postStream() {

        MediaType MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");

        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Nu\n");
                sink.writeUtf8("-----------------");
            }
        };
        Request request = new Request.Builder()
                .post(requestBody)
                .url(AppConfig.URL.post_text)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("onFailure:", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                assert response.body() != null;
                L.e(TAG, response.body().string());
            }
        });

    }

    /**
     * Post 方式提交文件
     */
    public void postFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory("msyt"), "test.md");
        // 格式： "text/x-markdown   text/plain"
        MediaType MARKDOWN = MediaType.parse("text/x-markdown");
        L.e(TAG, file.getAbsolutePath());
        Request request = new Request.Builder()
                .url(AppConfig.URL.post_text)
                .post(RequestBody.create(MARKDOWN, file))
                .build();
        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        L.e(TAG, "onFail");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        assert response.body() != null;
                        L.e(TAG, response.body().string());
                    }
                });
    }

    /**
     * 文件上传进度监控
     * 可以将文件分批次传输，主要是在 RequestBody 的 writeTo 方法中计算累计
     * 发送的文件字节数
     * 参考：https://juejin.im/post/5b441a745188251b1a7b1eb7#heading-0
     */

    public void fileUploadProgressMonitor() {

        RequestBody progressRequestBody = new RequestBody() {
            // 一次发送 2k 数据
            private static final int SEGMENT_SIZE = 2 * 1024;
            private File file = new File("文件的路径");

            private RecoverySystem.ProgressListener listener =
                    progress -> {
                        BigDecimal decimalSize = new BigDecimal(progress * 100);
                        BigDecimal decimalLen = new BigDecimal(contentLength());
                        Log.e(TAG, "完成：" + decimalSize.divide(decimalLen, RoundingMode.UP));
                    };

            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(@NotNull BufferedSink sink) throws IOException {
                Log.e("开始上传：", file.getCanonicalPath());
                Source source = null;
                try {

                    // 待发送文件转换为 Source
                    source = Okio.source(file);
                    int total = 0;
                    long read;
                    while ((read = source.read(sink.buffer(), SEGMENT_SIZE)) != -1) {
                        total += read;
                        sink.flush();
                        this.listener.onProgress(total);
                    }
                } finally {
                    Util.closeQuietly(source);
                }

            }

            @Override
            public long contentLength() {

                return file.length();
            }
        };

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "test", progressRequestBody)
                .build();
        Request request = new Request.Builder()
                .url("xxxx")
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                assert response.body() != null;
                Log.e("上传文件", response.body().string());
            }
        });


    }


    /**
     * 上传文件也是使用这个。
     * Post 方式提交分块请求
     * <p>
     * MultipartBuilder 可以构建复杂的请求体，与 HTML 文件上传形式兼容。每块请求体都是一个请求体，可以
     * 定义自己的请求头。这些请求头可以用来描述这块请求。例如，他们的 Content-Disposition。如果 Content-
     * Length 和 Content-Type 可用的话，他们会被自动添加到请求头中。
     */
    public void postMultipart() {
        MediaType mediaType = MediaType.parse("image/png");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition", "form-data;name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data;name=\"imge\""),
                        RequestBody.create(mediaType, new File("路径/logo.png"))
                ).
                        build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url("https://api.imgur.com/3/image")
                .build();
        try {
            mOkHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //上面这种写法的简写
    // 这种方式在后端通过使用 Spring 的 MultipartHttpServletRequest 去获取额外的参数
    public void postMultipartSimple() {
        MediaType mediaType = MediaType.parse("image/png");
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("title", "logo")
                .addFormDataPart("img", "logo.png", RequestBody.create(mediaType, new File(
                        "路径/logo.png")))
                .build();
    }

    /**
     * 上传文件使用 MultipartBody
     * 向服务器传递了一个键值对 username:sun 和一个文件 通过 MultipartBuilder 可以添加键值对或者文件。
     */
    public void upLoadFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory("test"), "xxx.mp4");
        MediaType mediaType_file = MediaType.parse("application/octet-stream");
        // Content-Disposition 可以用在消息体的子部分中，用来给出其对应字段的相关信息。
        // 作为 multipart body 中的消息头，第一个参数总是固定不变的 form-data; 附加的参数不区分大小写，并且拥有参数值，
        // 参数名与参数值用等号连接，参数之间用分号分隔。参数值用双引号括起来
        RequestBody requestBody = new MultipartBody.Builder()
                .addPart(Headers.of("Content-Dispositon", "form-data;name=\"username\""),
                        RequestBody.create(null, "sun"))
                .addPart(Headers.of("Content-Disposition", "form-data;name=\"mFile\";" +
                                "filename=\"xxx.mp4\""),
                        RequestBody.create(mediaType_file, file))
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {

            }
        });
    }

    /**
     * 响应缓存
     * <p>
     * 为了缓存响应，我们需要设置一个可以读写的缓存目录，和缓存大小。这个缓存目录应该是私有的，不允许其他不
     * 受信任的程序读取内容。
     * <p>
     * 一个缓存目录同时拥有多个缓存访问是错误的。大多数程序只需要条用一次 new OkHttp() 在第一次调用时配置好
     * 缓存，然后其他地方需要这个实例的时候调用就可以了。否则两个缓存实例相互干扰，破坏响应缓存，而且可能会
     * crash。
     * 响应缓存使用 HTTP 作为配置。你可以在请求头中添加 `Cache-Control:max-stale=3600` OkHttp 缓存会支持，
     * 你的服务通过响应头确定响应缓存多长时间。例如 `Cache-Control:max-age=9600`
     */
    public void cacheResponse(File cacheDirectory) {
        // 10 MB
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheDirectory, cacheSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .addHeader("Cache-Control", "max-age=3600")
                .build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        L.e(TAG, "failure");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        L.e(TAG, "reponse1:" + response);
                        L.e(TAG, "reponse1 cahce:" + response.cacheResponse());
                        L.e(TAG, "reponse1 network:" + response.networkResponse());
                    }
                });

        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        L.e(TAG, "failure");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        L.e(TAG, "reponse1:" + response);
                        L.e(TAG, "reponse1 cahce:" + response.cacheResponse());
                        L.e(TAG, "reponse1 network:" + response.networkResponse());
                    }
                });
    }

    /**
     * 取消一个 Call
     * <p>
     * 使用 `Call.cancel()` 可以立即停止掉一个正在执行的 Call。如果一个线程正在写请求或者读响应，将会
     * 引发 `IOException` 当 `call` 没有必要的时候，使用这个 `api` 可以节约网络资源。例如当用户离开一个应用
     * 时。不管同步还是异步请求都可以取消。
     * <p>
     * 你可以通过 tags 来同时取消多个请求。当构建一个请求的时候，使用 `RequestBuilder.tag(tag)` 来分配一个标签。
     * 之后就可以通过这个标签来取消这个带 tag 的 call 了。
     */
    public void cancelCall() {


    }

    /**
     * 超时
     */
    public void configureTimeOut() {
        // 只会修改这些内容，没有改变的都是默认的。
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

    }

    /**
     * 使用 OkHttpClient 所有的 HTTP Client 配置包括：代理、超时设置等等。当你需要为单个 call
     * 改变配置的时候，使用 newBuilder 来创建一个新的 OkHttpclient 。这个和之前的共用线程池
     * 和其他配置。
     */
    public void newConfigure() {
        mOkHttpClient.newBuilder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .build();

    }


    public void applicationInterceptor() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response;
                try {
                    response = client.newCall(request).execute();
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void netInterceptor() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response;
                try {
                    response = client.newCall(request).execute();
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            if (IS_LOG_OK_HTTP)
                Log.e("request:", String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            if (IS_LOG_OK_HTTP)
                Log.e("request:", String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

}
