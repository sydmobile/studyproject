package com.study.study_module.mvp.my_mvp.module_demo;

import com.study.study_module.retrofit.Topics;

import rx.functions.Action1;

/**
 * 说明：TestPresenter
 * <p>
 * date: 2020/4/14 18:07
 *
 * @author syd
 * @version 1.0
 */
public class TestPresenter extends TestContract.Presenter {

    public TestPresenter(TestContract.View view){
        this.view = view;
        this.model = createModel();
    }

    /**
     * 方法1
     */
    @Override
    void request1() {
        model.request().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                view.updateUI1(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    @Override
    void request2() {

    }

    @Override
    public TestContract.Model createModel() {
        return new TestModel(new TestContract.Callback() {
            @Override
            public void callback1(Topics topics) {
                view.updateUI1(topics.getData().toString());
            }

            @Override
            public void callback2() {

            }

            @Override
            public void callback3() {

            }
        });
    }
}
