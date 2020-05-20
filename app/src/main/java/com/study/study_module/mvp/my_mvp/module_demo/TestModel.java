package com.study.study_module.mvp.my_mvp.module_demo;

import com.study.study_module.retrofit.HotTopicService;
import com.study.study_module.retrofit.Retrofit2BaseUseActivity;
import com.study.study_module.retrofit.Topics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 说明：TestModel
 * <p>
 * date: 2020/4/14 18:17
 *
 * @author syd
 * @version 1.0
 */
public class TestModel implements TestContract.Model {

    private TestContract.Callback callback;
    private HotTopicService service =
            Retrofit2BaseUseActivity.retrofit.create(HotTopicService.class);

    TestModel(TestContract.Callback callback) {
        this.callback = callback;

    }

    /**
     * 请求网络连接
     */
    @Override
    public void getData1() {
        service.getHotTopic().enqueue(new Callback<Topics>() {
            @Override
            public void onResponse(Call<Topics> call, Response<Topics> response) {
                if (response.isSuccessful()) {
                    callback.callback1(response.body());
                } else {
                    callback.callback3();
                }
            }

            @Override
            public void onFailure(Call<Topics> call, Throwable t) {
                callback.callback2();
            }
        });
    }


    @Override
    public void getData2() {

    }

    @Override
    public void getData3() {

    }

    @Override
    public Observable<String> request() {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(2000);
                    subscriber.onNext("从网络获取到的数据");
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
