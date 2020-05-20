package com.study.study_module.rx.retrofit_rxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 说明：将一些重复的操作提出来，放到父类中
 * <p>
 * date: 2020/4/17 16:18
 *
 * @author syd
 * @version 1.0
 */
public class ObjectLoader {

    protected <T> Observable<T> observe(Observable<T> observable){

        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
