package com.syd.study.net;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.config.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：网络请求实验
 * <p>
 * date: 2019/6/14 14:39
 *
 * @author syd
 * @version 1.0
 */
public class NetActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_net)
    TextView tvNet;
    RequestQueue requestQueue;
    private final StringRequest stringRequest;

    public NetActivity() {
        stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL.url_result,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse", "1" + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("onResponse", "2");
                        Log.e("onResponse", "3");
                        Log.e("onResponse", "4");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("onResponse", "5");
                        Log.e("onResponse", "6");
                        Log.e("onResponse", "7");
                        log("onResponse","res");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley_error",error.getMessage()+"");
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
        init();
        initListener();
    }

    private void init() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        sendThread();
    }

    private void initListener() {
        tvNet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_net:
                sendThread();
//                Log.e("click", "one" + Thread.currentThread().getName());
//                Log.e("click", "two" + Thread.currentThread().getName());
//                Log.e("click", "two1" + Thread.currentThread().getName());
//                Log.e("click", "two2" + Thread.currentThread().getName());
//                Log.e("click", "two3" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                Log.e("click", "two4" + Thread.currentThread().getName());
//                log("click", "content");
                break;
        }
    }

    public void log(String flag, String content) {
        for (int i = 0; i < 10; i++) {

            Log.e(flag, content+i+(i+1));
        }
    }
    int second = 5;
    public void sendThread(){
        second = 5;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (second != 0 ) {
                    try {
                        Thread.sleep(1000);
                        second--;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               Log.e("runnable","=====");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
