package com.study.study_module.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;
import com.study.userful.util.L;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;

/**
 * 说明：传感器运用
 * <p>
 * date: 2019/7/9 16:26
 *
 * @author syd
 * @version 1.0
 */
public class SensorsActivity extends BaseActivity implements SensorEventListener {

    @BindView(R.id.tv_orientation)
    TextView tvOrientation;
    @BindView(R.id.tv_magnetic)
    TextView tvMagnetic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_sensor;
    }


    @Override
    public void initData() {
        initSensor();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initNetData() {

    }

    private void initSensor() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);

        Sensor sensorMagneticField = sensorManager.getDefaultSensor(TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this,sensorMagneticField,SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION:
                L.e(TAG, "x:" + event.values[0] + "==y:" + event.values[1] + "==z:" + event.values[2]);
                tvOrientation.setText(String.format("x:%s==y:%s==z:%s", event.values[0], event.values[1],
                        event.values[2]));
                break;
            case TYPE_MAGNETIC_FIELD:


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        L.e(TAG, accuracy + "");
    }


    private void calculateOrientation(){
        float [] values  =new float[3];
        float [] R = new float[9];
    }
}
