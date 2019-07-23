package com.syd.study.okhttp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.config.AppConfig;
import com.syd.study.util.L;

import java.io.IOException;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 说明： ok http 基础使用
 * <p>
 * date: 2019/7/23 16:57
 *
 * @author syd
 * @version 1.0
 */
public class OkHttpBaseUseActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bt_get)
    Button btGet;
    @BindView(R.id.bt_post)
    Button btPost;
    @BindView(R.id.bt_json)
    Button btJson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_base_use);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void initListener() {
        super.initListener();
        btGet.setOnClickListener(this);
        btPost.setOnClickListener(this);
        btJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_json:
                jsonPost();
                break;
            case R.id.bt_get:
                goGet();
                break;
        }

    }

    public void goGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(AppConfig.URL.url_get)
                        .build();
                try {
                    // 同步执行
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        // .body 获取响应体的内容
                        L.e(TAG, response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    L.e(TAG, "IOException:" + e.getMessage());
                }
            }
        }).start();

    }

    public void jsonPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                        .post(requestBody).build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        L.e(response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
