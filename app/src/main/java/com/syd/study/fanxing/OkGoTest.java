package com.syd.study.fanxing;

/**
 * 说明：$d$
 * <p>
 * date: 2019/7/26 15:30
 *
 * @author syd
 * @version 1.0
 */
public class OkGoTest<T> {

    public static <T> PostRequestTest<T> post(String url) {
        return new PostRequestTest<>();
    }

    public T post(){
        return (T) this;
    }
}
