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
        public static final String url_get = "https://wanandroid.com/wxarticle/chapters/json";
        // post 请求  参数 ：k：关键字  例如  k：android
        public static final String url_post = "https://www.wanandroid.com/article/query/0/json";

        // 微信公众号历史文章  408 为公众号id  1 为页码
        public static final String wxarticle_list = "https://wanandroid.com/wxarticle/list/408/{page}/json";

        // 图片
        public static final String imgs = "https://gank.io/api/v2/data/category/Girl/type/Girl/page/1" +
                "/count/10";

        // 网易新闻  参数：0-10  10-10  20-10 30-10 .....
        public static String news = "http://c.m.163.com/nc/article/headline/T1348647853363/0-10" +
                ".html";


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
