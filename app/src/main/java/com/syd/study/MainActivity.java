package com.syd.study;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syd.study.lambda.LambdaActivity;
import com.syd.study.net.NetActivity;
import com.syd.study.recyclerview.ListViewActivity;
import com.syd.study.recyclerview.RecyclerViewActivityOne;
import com.syd.study.sensor.SensorActivity;
import com.syd.study.testuses.TestActivity;
import com.syd.study.textview.TextViewActivity;
import com.syd.study.viewstub.ViewStubActivity;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btLambda;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_rlv)
    TextView tvRlv;
    @BindView(R.id.tv_net)
    TextView tvNet;
    @BindView(R.id.tv_list)
    TextView tvList;
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.tv_net_test)
    TextView tvNetTest;
    @BindView(R.id.tv_sensor)
    TextView mTvSensor;
    private static final String TAG = "MainActivity";
    @BindView(R.id.bt_textview)
    Button mBtTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        TextView textView = findViewById(R.id.tv);
        textView.setOnClickListener(this);
        btLambda = findViewById(R.id.bt_lambda);
        btLambda.setOnClickListener(this);

        tvRlv.setOnClickListener(this);
        tvNet.setOnClickListener(this);
        tvList.setOnClickListener(this);
        tvTest.setOnClickListener(this);
        tvNetTest.setOnClickListener(this);
        mTvSensor.setOnClickListener(this);
        mBtTextview.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                Intent intent = new Intent(this, ViewStubActivity.class);
                startActivity(intent);
                ThreadPoolExecutor threadPoolExecutor =
                        new ThreadPoolExecutor
                                (5, 10, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(2));
                // corePoolSize 线程池中核心线程的数量
                // maximumPoolSize 线程中最大线程数量
                // keepAliveTime 非核心线程闲置时间 ThreadPoolExecutor 的 allowCoreThreadTimeOut 属性设置为 true 则该参数也表示
                // 核心线程的超时时长
                // Unit 第三个的单位
                // workQueue
                break;
            // recyclerView
            case R.id.tv_rlv:
                Intent intent1 = new Intent(this, RecyclerViewActivityOne.class);
                startActivity(intent1);
                break;
            case R.id.tv_net:
                Intent intent2 = new Intent(this, NetActivity.class);
                startActivity(intent2);
                break;
            // ListView
            case R.id.tv_list:
                Intent intent3 = new Intent(this, ListViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_lambda:
                Intent intent5 = new Intent(this, LambdaActivity.class);
                startActivity(intent5);
                break;
            // 测试使用
            case R.id.tv_test:
                Intent intent4 = new Intent(this, TestActivity.class);
                startActivity(intent4);
                break;
            // testNet
            case R.id.tv_net_test:
                String url = "http://192.168.1.100:8080/nav/hospitals";
                Log.e("start", System.currentTimeMillis() + "");
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse", System.currentTimeMillis() + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onResponse", "error");
                    }
                }));
                break;

            // sensor
            case R.id.tv_sensor:
                Intent intent6 = new Intent(this, SensorActivity.class);
                startActivity(intent6);
                break;

            case R.id.bt_textview:
                Intent intent7 = new Intent(this, TextViewActivity.class);
                startActivity(intent7);
                break;

        }
    }


}
