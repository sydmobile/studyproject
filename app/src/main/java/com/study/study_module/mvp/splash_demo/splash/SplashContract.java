package com.study.study_module.mvp.splash_demo.splash;

import com.study.study_module.mvp.splash_demo.BasePresenter;
import com.study.study_module.mvp.splash_demo.BaseView;

/**
 * 说明：SplashContract
 * <p>
 * date: 2020/4/14 16:10
 *
 * @author syd
 * @version 1.0
 */
public interface SplashContract {

    interface Presenter extends BasePresenter<View>{
        void initData();
    }

    interface View extends BaseView{
        void toMainActivity();
    }
}
