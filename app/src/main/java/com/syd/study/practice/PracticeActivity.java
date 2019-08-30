package com.syd.study.practice;

import android.os.Bundle;

import com.syd.study.base.BaseActivity;

import java.io.IOException;

import androidx.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 说明：$d$
 * <p>
 * date: 2019/8/9 9:48
 *
 * @author syd
 * @version 1.0
 */
public class PracticeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void okHttpPractice() {
        // 1. 创建默认的客户端，
        // 默认网络失败重新连接，超时时间 10 s 中。不启用缓存
        OkHttpClient okHttpClient = new OkHttpClient();


        // File  byte[] content String ByteString byte[]
        // application/json;charset=utf-8   application/octet-stream  text/plain  image/png text/x-markdown

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "");

        // application/json;charset=utf-8  application/octet-stream  text/plain image/png text/x-markdown

        // 2. 构建请求报文

        Request request = new Request.Builder()
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("key0", "value0")
                .add("key1", "value1")
                .build();
        Request request1 = new Request.Builder().post(formBody)
                .url("请求 url")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "233232-dbc2-332232")
                .addHeader("Host", "39.106.24.148:8080")
                .addHeader("accept-encoding", "gzip,deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("cache-control", "no-cache")
                .build();
        // "application/x-www-form-urlencoded"
        // "application/x-www-form-urlencoded"
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        // key0=value0&key1=value1
        RequestBody body = RequestBody.create(mediaType, "key0=value0&key1=value1");
        Request request2 = new Request.Builder()
                .url("http://39.106.24.148:8080/test")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        okHttpClient.dispatcher().executorService().shutdown();

        okHttpClient.connectionPool().evictAll();
        try {
            okHttpClient.cache().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // application/json;charset=utf-8  text/plain image/png application/octet-stream text/x-markdown

        // height-level media type,such as "text" "image" "audio" "video" "application"

        // height level

        MediaType mediaType1 = MediaType.parse("application/json;charset=utf-8");

        MediaType mediaType2 = MediaType.parse("application/json;charset=utf-8");
        MediaType mediaType3 = MediaType.parse("text/plain;charset=utf-8");
        MediaType mediaType4 = MediaType.parse("image/png");
        MediaType mediaType5 = MediaType.parse("application/octet-stream");
        MediaType mediaType6 = MediaType.parse("application/octet-stream");


        String json = "application/json;charset=utf-8";
        String plain = "text/plain;charset=utf-8";
        String img = "image;png";
        String octet = "application/octet-stream";


        // height level media type such as "text" "image" "audio"  "video" "application"
        // specific media subtype,such as "plain" "png" "mpeg" "mp4" "xml"

        // text/plain  image/png audio/mpeg video/map4 application/xml


    }
}
