package com.study.study_module.mvp.my_mvp.module_demo;

import com.study.study_module.mvp.my_mvp.BaseModel;
import com.study.study_module.mvp.my_mvp.BasePresenter;
import com.study.study_module.mvp.my_mvp.BaseView;
import com.study.study_module.retrofit.Topics;

import rx.Observable;

/**
 * 说明：TestContract
 * <p>
 * date: 2020/4/14 18:03
 *
 * @author syd
 * @version 1.0
 */
public interface TestContract {

    interface Model extends BaseModel{
        void getData1();
        void getData2();
        void getData3();
        Observable<String> request();

    }

    interface View extends BaseView{
        void updateUI1(String content);
        void updateUI2();
        void updateUI3();
    }


    abstract class Presenter extends BasePresenter<View,Model>{
        abstract void request1();
        abstract void request2();
    }

    interface Callback{
        void callback1(Topics topics);
        void callback2();
        void callback3();
    }

}
