package com.study.study_module.rx.retrofit_rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.study.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 说明：$
 * <p>
 * date: 2020/4/17 16:25
 *
 * @author syd
 * @version 1.0
 */
public class WanAndroidActivity extends AppCompatActivity {

    @BindView(R.id.et_test_data)
    EditText etTestData;
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        WanAndroidLoader wanAndroidLoader = new WanAndroidLoader();
        Observable<WXdata> wan = wanAndroidLoader.getWxArticle("408", "1");
        Observable observable = wanAndroidLoader.getWxArticle("408", "1");

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Log.e("call", "Thread:" + Thread.currentThread());
                Drawable drawable = getDrawable(R.drawable.arrow);
                subscriber.onNext(drawable);
            }

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", "Thread:" + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", "Thread:" + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        Log.e("onNext", "Thread:" + Thread.currentThread());
                    }
                });
        new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        };
        Log.e("observable", "====" + observable);
//                .subscribe(new Action1<WXdata>() {
//                    @Override
//                    public void call(WXdata wxArticle) {
//                        Log.e("ssss","success");
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        if (throwable instanceof Fault){
//                            Fault fault = (Fault) throwable;
//                            if (fault.getErrorCode() !=0){
//                                Log.e("ss",fault.getErrorMsg());
//                            }
//                        }else {
//                            Log.e("其他","异常");
//                        }
//                    }
//                });
    }
}
