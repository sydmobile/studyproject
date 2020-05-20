package com.study.config;

/**
 * 说明：常量内容
 * <p>
 * date: 2019/6/14 15:27
 *
 * @author syd
 * @version 1.0
 */
public class AppConfig {

    public static class URL {

        // get 请求 无参数
        public static String url_get = "https://wanandroid.com/wxarticle/chapters/json";
        // post 请求  参数 ：k：关键字  例如  k：android
        public static String url_post = "https://www.wanandroid.com/article/query/0/json";


        public static String json_request = "http://39.106.24.148:8080/experiment/mock/locator" +
                "/data/collection";

        public static String post_request = "http://39.106.24.148:8080/nav/map/fmap";

        public static String get_txt = "http://publicobject.com/helloworld.txt";
        // 提取请求头、响应头
        public static String header = "https://api.github.com/repos/square/okhttp/issues";
        // text
        public static String post_text = "https://api.github.com/markdown/raw";
        // form
        public static String post_form = "https://en.wikipedia.org/w/index.php";

        public static String base_url_wanandroid = "https://wanandroid.com/";

        // get 请求
        public static String wx_account_all = "https://wanandroid.com/wxarticle/chapters/json";

        // get 请求 常用网址
        public static String common_web_side = "https://www.wanandroid.com/friend/json";

        // 登录
        public static String login = "user/login";


    }
}
