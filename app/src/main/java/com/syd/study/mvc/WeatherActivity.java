package com.syd.study.mvc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.mvc.callback.Callback1;
import com.syd.study.mvc.model.WeatherModel;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 14:47
 *
 * @author syd
 * @version 1.0
 */
public class WeatherActivity extends BaseActivity {
    @BindView(R.id.bt_click)
    Button btClick;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    WeatherModel weatherModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
        weatherModel = new WeatherModel();
        weatherModel.getWeather("s", new Callback1<WeatherModel.Weather>() {
            @Override
            public void onCallBack(WeatherModel.Weather weather) {
                tvAge.setText(weather.getStatus());
            }
        });
    }
}
