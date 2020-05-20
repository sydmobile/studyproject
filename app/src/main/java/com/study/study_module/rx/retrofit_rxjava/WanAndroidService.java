package com.study.study_module.rx.retrofit_rxjava;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 说明：网络请求定义
 * <p>
 * date: 2020/4/17 15:06
 *
 * @author syd
 * @version 1.0
 */
public interface WanAndroidService {
    /**
     * https://wanandroid.com/wxarticle/list/408/1/json
     * 方法：GET
     * 参数：
     * 	公众号 ID：拼接在 url 中，eg:405
     * 	公众号页码：拼接在url 中，eg:1
     */

    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<WXdata>> getArticle(@Path("id")String id,@Path("page")String page);


    /**
     * https://wanandroid.com/wxarticle/chapters/json
     * 方法： GET
     */
    @GET("wxarticle/chapters/json")
    Observable<WXPublic> getWXPublicList();

}
