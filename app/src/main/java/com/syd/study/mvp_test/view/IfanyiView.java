package com.syd.study.mvp_test.view;

/**
 * 说明：$
 * <p>
 * date: 2019/9/29 17:18
 *
 * @author syd
 * @version 1.0
 */ // View 接口：IfanyiView
public interface IfanyiView {
    void init();//初始化

    void setInfo(String str);//输出翻译信息

    void setError();//输出出错信息
}
