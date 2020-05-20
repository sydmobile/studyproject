package com.study.study_module.mvp_test.model;

import android.content.Context;

import com.study.study_module.mvp_test.presenter.OnFanyiListener;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 17:54
 *
 * @author syd
 * @version 1.0
 */
public class FanyiModel implements Ifanyi {
    private Fanyi fy = new Fanyi();

    public FanyiModel(){

    }

    @Override
    public void handleData(String input, Context context, OnFanyiListener listener) {

        // 具体的处理业务，根据结果调用不同的方法。

    }

    @Override
    public String fanyiToString(Fanyi fy) {
        return "toString";
    }
}
