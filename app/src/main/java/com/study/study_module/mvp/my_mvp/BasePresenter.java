package com.study.study_module.mvp.my_mvp;

/**
 * 说明：BasePresenter
 * <p>
 * date: 2020/4/14 17:31
 *
 * @author syd
 * @version 1.0
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V view;
    protected M model;


    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }

    public abstract M createModel();

    /**
     * 检查 view 是否可以用
     * @return 是否可用
     */
    public boolean checkView(){
        return (view != null);
    }
}
