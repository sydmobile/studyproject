package com.study.study_module.retrofit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 说明：将一些重复的操作提出来，放到父类以免 WanAndroidLoader 里每个接口都有重复代码
 * <p>
 * date: 2020/1/8 14:27
 *
 * @author syd
 * @version 1.0
 */
public class ObjectLoader {

    protected <T>Observable<T> observe(Observable<T> observable){
        return  observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
