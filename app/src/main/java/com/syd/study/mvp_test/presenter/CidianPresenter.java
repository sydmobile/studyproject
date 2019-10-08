package com.syd.study.mvp_test.presenter;

import android.content.Context;

import com.syd.study.mvp_test.model.FanyiModel;
import com.syd.study.mvp_test.view.IfanyiView;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 16:58
 *
 * @author syd
 * @version 1.0
 */
public class CidianPresenter implements OnFanyiListener,ICidianPresenter{

    // 1.声明 View 层对应接口、Model 层对应的类
    IfanyiView fyV;
    FanyiModel fanyiModel;

    //2.重构函数，初始化 View 接口实例、Model 实例
    public CidianPresenter(IfanyiView fyV){
        this.fyV = fyV;
        fanyiModel = new FanyiModel();
    }

    // 3.将 View 层获得的数据传入 Model 层
    @Override
    public void inputToModel(String input, Context context) {
        fanyiModel.handleData(input,context,this);
    }

    // 回调函数，调用 UI 更新
    public void onSuccess(String str){
        fyV.setInfo(str);
    }

    // 回调函数，调用 UI 输出出错信息
    public void onError(){
        fyV.setError();
    }



}

