package com.syd.study.eventbus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：EventBus 第一个 Activity
 * <p>
 * date: 2019/11/6 8:54
 *
 * @author syd
 * @version 1.0
 */
public class FistEventBusActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "FistEventBusActivity";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_click)
    Button btClick;
    boolean isShow = false;
    @BindView(R.id.bt_show)
    Button btShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_first);
        ButterKnife.bind(this);
        init();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        super.initView();
        btClick.setOnClickListener(this);
        btShow.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.tb);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        if (isShow) {
            new AlertDialog.Builder(this).setTitle("title").setMessage("info").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerMessage(String info) {
        Log.e(TAG, info);
        tvTitle.setText(info);
        isShow = true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_click:
                EventBus.getDefault().postSticky("来自FistActivity");
                Intent intent = new Intent(this, SecondEventBusActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_show:
                new AlertDialog.Builder(this).setTitle("title").setMessage("info").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
        }
    }
}
