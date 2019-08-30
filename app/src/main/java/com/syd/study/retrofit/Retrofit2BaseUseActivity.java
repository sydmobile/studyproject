package com.syd.study.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.config.AppConfig;
import com.syd.study.util.L;

import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 说明：Retrofit 基本使用
 * <p>
 * date: 2019/7/29 17:11
 *
 * @author syd
 * @version 1.0
 */
public class Retrofit2BaseUseActivity extends BaseActivity implements View.OnClickListener {

//    @BindView(R.id.bt_get)
//    Button btGet;
//    @BindView(R.id.bt_post)
//    Button btPost;
//    @BindView(R.id.bt_json)
//    Button btJson;
//    @BindView(R.id.get_txt)
//    Button getTxt;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_retrofit_base_use);
//        ButterKnife.bind(this);
//        init();
//    }
//
//    @Override
//    public void initListener() {
//        super.initListener();
//        btGet.setOnClickListener(this);
//    }
//
    @Override
    public void onClick(View v) {

    }
//
    public void baseUse(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConfig.URL.url_get)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        IUserBiz userBiz = retrofit.create(IUserBiz.class);
//        Call<List<User>> call = userBiz.getUsers();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                L.e(TAG,"normal_get"+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//
//            }
//        });

    }
}
