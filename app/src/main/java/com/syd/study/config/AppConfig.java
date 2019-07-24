package com.syd.study.config;

/**
 * 说明：$d$
 * <p>
 * date: 2019/6/14 15:27
 *
 * @author syd
 * @version 1.0
 */
public class AppConfig {

    public static class URL {
        // 结果
        public static String url_result = "https://easy-mock.com/mock/5ceca84112f1886d38826cd0/app/result";
        // 列表形式
        public static String url_list = "https://easy-mock.com/mock/5ceca84112f1886d38826cd0/app/list";


        public static String url_get = "https://easy-mock.com/mock/5ceca84112f1886d38826cd0/app/getmethod";

        public static String json_request = "http://39.106.24.148:8080/experiment/mock/locator/data/collection";

        public static String post_request = "http://39.106.24.148:8080/nav/map/fmap";

        public static String get_txt = "http://publicobject.com/helloworld.txt";
        // 提取请求头、响应头
        public static String header = "https://api.github.com/repos/square/okhttp/issues";
        // text
        public static String post_text = "https://api.github.com/markdown/raw";
        // form
        public static String post_form = "https://en.wikipedia.org/w/index.php";
    }
}
