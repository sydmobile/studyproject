package com.syd.study.observer;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * 说明：$
 * <p>
 * date: 2019/10/10 16:14
 *
 * @author syd
 * @version 1.0
 */
public class People implements Observer {
    private String name;

    public People(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Float price = (Float) arg;
        Log.e("update", "我是观察者" + name + "房子价格变了：" + price);
        if (price > 20000) {
            Log.e("update:", "太TM贵啦，不关注了！");
            o.deleteObserver(this);
        }
    }
}
