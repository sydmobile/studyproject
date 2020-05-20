package com.study.study_module.mvc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;
import com.study.study_module.mvc.callback.Callback1;
import com.study.study_module.mvc.model.WeatherModel;

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
        weatherModel = new WeatherModel();
        weatherModel.getWeather("s", new Callback1<WeatherModel.Weather>() {
            @Override
            public void onCallBack(WeatherModel.Weather weather) {
                tvAge.setText(weather.getStatus());
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_simple;
    }
}
