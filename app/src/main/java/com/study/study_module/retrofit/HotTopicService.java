package com.study.study_module.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 说明：
 * <p>
 * date: 2019/7/29 17:08
 *
 * @author syd
 * @version 1.0
 */
public interface HotTopicService {

    // 参数使用 @Query("start) 注解

    @GET("topic")
    Call<Topics> getHotTopic();


    @FormUrlEncoded
    @POST("http://192.168.1.122/travel/route/pageQuery")
    Call<TravelRoute> getTravelRoute(@Field("rname") String rname);

    // 豆瓣 base_url https://api.douban.com/v2/movie/

    @GET("topic")
    Observable<Topics> getTopic();

    // 上传单个文件
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadFile(@Part("description") RequestBody description,
                                  @Part MultipartBody.Part file);

    // 上传多个文件
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadMultipleFiles(@Part("description") RequestBody description,
                                           @Part MultipartBody.Part file1,
                                           @Part MultipartBody.Part file2);

}
