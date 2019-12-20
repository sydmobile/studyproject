package com.syd.study.mvp;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 17:21
 *
 * @author syd
 * @version 1.0
 */
public interface Contract {

    interface IModel extends BaseModel {
        void getData(Callback callback);

        void login(String username, String password, Callback callback);
    }

    interface IView extends BaseView {
        void updateUI();
    }

    interface Callback {
        void onResult(String text);
    }

    abstract class Presenter extends BasePresenter<IView, IModel> {
        public abstract void login(String username, String password);
    }

}
