package com.study.study_module.eventbus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：EventBus SecondActivity
 * <p>
 * date: 2019/11/6 9:15
 *
 * @author syd
 * @version 1.0
 */
public class SecondEventBusActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_click)
    Button btClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_eventbus_second;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart()");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initView() {
        super.initView();
        btClick.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    public void handlerMessage(String info){
        Log.e(TAG,info);
        tvTitle.setText(info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_click:
                finish();

                EventBus.getDefault().post("来自第二个 Activity");
                break;

        }
    }
}
