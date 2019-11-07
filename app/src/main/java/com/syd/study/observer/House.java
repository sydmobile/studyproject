package com.syd.study.observer;

import java.util.Observable;

import androidx.annotation.NonNull;

/**
 * 说明：房子
 * <p>
 * date: 2019/10/10 16:12
 *
 * @author syd
 * @version 1.0
 */
public class House extends Observable {
    private float house_price;

    public House(float house_price) {
        this.house_price = house_price;
    }

    public void updatePrice(float house_price) {
        this.house_price = house_price;
        // 设置变化点
        setChanged();
        // 通知观察者
        notifyObservers(house_price);
    }

    @NonNull
    @Override
    public String toString() {
        return "当前房子价格：" + house_price;
    }
}
