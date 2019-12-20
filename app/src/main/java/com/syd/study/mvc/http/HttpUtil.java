package com.syd.study.mvc.http;


import com.syd.study.mvc.model.SampleModel;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 13:54
 *
 * @author syd
 * @version 1.0
 */
public class HttpUtil {
    public static SampleModel.UserInfo get(String uid){
        SampleModel.UserInfo userInfo = new SampleModel.UserInfo();
        userInfo.setName("张三");
        userInfo.setAge(18);
        return userInfo;
    }

}
