package com.study.study_module.mvc.model;

import com.study.study_module.mvc.callback.Callback1;
import com.study.study_module.mvc.http.HttpUtil;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 13:50
 *
 * @author syd
 * @version 1.0
 */
public class SampleModel implements BaseModel {

    public void getUserInfo(String uid, Callback1<UserInfo> callback1) {
        UserInfo userInfo = HttpUtil.get(uid);
        callback1.onCallBack(userInfo);
    }

    @Override
    public void onDestroy() {

    }

    public static class UserInfo {
        private String name;
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
