package com.study.utils.volley;

import java.util.HashMap;

/**
 * 说明：存放参数（请求头，请求参数）对象
 * <p>
 * date: 2020/5/2 16:19
 *
 * @author syd
 * @version 1.0
 */
@SuppressWarnings("WeakerAccess")
public class Parameter extends HashMap<String, String> {

    /**
     * 添加参数
     *
     * @param key   key
     * @param value value
     * @return this
     */
    public Parameter add(String key, String value) {
        put(key, value);
        return this;
    }

    /**
     * 参数拼接 user=syd&age=18
     *
     * @return string
     */
    public String toParameterString() {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : entrySet()) {
            result.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if (result.toString().endsWith("&")) {
            result = new StringBuilder(result.substring(0, result.length() - 1));
        }
        return result.toString();
    }
}
