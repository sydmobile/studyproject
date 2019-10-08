package com.syd.study.mvp_test.model;

import android.content.Context;

import com.syd.study.mvp_test.presenter.OnFanyiListener;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 17:46
 *
 * @author syd
 * @version 1.0
 */
public interface Ifanyi {

    void handleData(String input, Context context ,final OnFanyiListener listener);

    String fanyiToString(Fanyi fy);
}
