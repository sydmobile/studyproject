package com.syd.study.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 说明：$d$
 * <p>
 * date: 2019/7/29 17:08
 *
 * @author syd
 * @version 1.0
 */
public interface IUserBiz {

    @GET("users")
    Call<List<User>> getUsers();


}
