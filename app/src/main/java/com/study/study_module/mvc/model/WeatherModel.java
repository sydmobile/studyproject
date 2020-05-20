package com.study.study_module.mvc.model;

import com.study.study_module.mvc.callback.Callback1;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 14:41
 *
 * @author syd
 * @version 1.0
 */
public class WeatherModel implements BaseModel {
    @Override
    public void onDestroy() {

    }


    public void getWeather(String cityNumber, Callback1<Weather> callback1){
        Weather weather = new Weather();
        callback1.onCallBack(weather);
    }


    public class Weather{
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String status;
    }
}
