package com.study.study_module.material;

import com.study.config.AppConfig;
import com.study.entity.WXArticleListEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;

/**
 * 说明：获取列表
 * <p>
 * date: 2020/5/28 14:03
 *
 * @author syd
 * @version 1.0
 */
public interface ApiServer {
    @GET(AppConfig.URL.wxarticle_list)
    Observable<WXArticleListEntity> getList(@Path("page") int page);
}
