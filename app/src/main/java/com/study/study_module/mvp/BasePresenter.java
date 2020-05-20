package com.study.study_module.mvp;

/**
 * 说明：Presenter 基类
 * <p>
 * date: 2019/11/28 15:55
 *
 * @author syd
 * @version 1.0
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V view;
    protected M model;

    public BasePresenter() {
        model = createModel();
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;

    }

    public abstract M createModel();
}
