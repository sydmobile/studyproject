package com.study.study_module.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.study.R;
import com.study.base.BaseActivity;
import com.study.userful.util.L;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明： 传感器相关测试
 * <p>
 * date: 2019-07-15 14:01
 *
 * @author syd
 * @version 1.0
 */
public class SensorActivity extends BaseActivity implements View.OnClickListener, SensorEventListener {

    @BindView(R.id.et_test_data)
    EditText etTestData;
    @BindView(R.id.tv_test)
    TextView mTvTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_test;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    public void init() {
        initView();
        initListener();
    }

    public void initView() {
        L.e("start");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this, sensor, 1000000,5000000);
    }

    public void initListener() {
        mTvTest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_test:

                break;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        L.e(TAG, "onSensorChanged:" + event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        L.e(TAG, "onAccuracyChanged:" + accuracy);
    }

    private Sensor sensor;
    private SensorManager sensorManager;
}
