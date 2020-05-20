package com.study.study_module.retrofit;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;

import java.io.File;

import androidx.annotation.Nullable;
import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static java.lang.String.format;

/**
 * 说明：Retrofit 基本使用
 * <p>
 * date: 2019/7/29 17:11
 *
 * @author syd
 * @version 1.0
 */
public class Retrofit2BaseUseActivity extends BaseActivity implements View.OnClickListener {

    public static final String BASE_URL = "https://api.readhub.cn/";
    // 普通 Retrofit
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    // 和 Rx 结合的 Retrofit
    public static Retrofit retrofit_rx = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    // 练习
    Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @BindView(R.id.bt_get)
    Button btGet;
    @BindView(R.id.bt_post)
    Button btPost;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bt_rx_get)
    Button btRxGet;
    @BindView(R.id.bt_rx_post)
    Button btRxPost;

    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int layoutId() {
        return R.layout.activity_retrofit_base_use;
    }

    @Override
    public void initListener() {
        super.initListener();
        btGet.setOnClickListener(this);
        btPost.setOnClickListener(this);
        btRxGet.setOnClickListener(this);
        btRxPost.setOnClickListener(this);
    }

    //
    @Override
    public void onClick(View v) {
        Log.e("currentThread:", Thread.currentThread().toString());
        switch (v.getId()) {
            case R.id.bt_get:
                get();
                break;
            case R.id.bt_post:
                post();
                break;
            case R.id.bt_rx_get:
                get_rx();
                break;
        }


    }

    /**
     * 最基本的 get  请求
     */
    public void get() {
        HotTopicService hotTopicService = retrofit.create(HotTopicService.class);
        Call<Topics> call = hotTopicService.getHotTopic();
        call.enqueue(new Callback<Topics>() {
            @Override
            public void onResponse(Call<Topics> call, Response<Topics> response) {
                Topics topics = response.body();
                tvContent.setText(topics.getData().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<Topics> call, Throwable t) {
                tvContent.setText(format("失败：%s", t.getMessage()));
                t.printStackTrace();
            }
        });
    }

    /**
     * 最基本的 post 请求
     */
    public void post() {
        HotTopicService hotTopicService = retrofit.create(HotTopicService.class);
        Call<TravelRoute> call = hotTopicService.getTravelRoute("春节");
        call.enqueue(new Callback<TravelRoute>() {
            @Override
            public void onResponse(Call<TravelRoute> call, Response<TravelRoute> response) {
                try {
                    tvContent.setText(response.body().getList().get(0).getRname());
                } catch (Exception e) {
                    tvContent.setText("出现异常");
                }
                //
            }

            @Override
            public void onFailure(Call<TravelRoute> call, Throwable t) {
                t.printStackTrace();
                tvContent.setText(format("失败：%s", t.getMessage()));
            }
        });
    }

    public void get_rx() {
        HotTopicService hotTopicService = retrofit_rx.create(HotTopicService.class);
        hotTopicService.getTopic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Topics>() {
                    @Override
                    public void onCompleted() {
                        Log.e("completed", "===");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", e.getMessage());
                    }

                    @Override
                    public void onNext(Topics topics) {
                        Log.e("onNext", "===");
                        tvContent.setText(topics.toString());
                    }
                });

    }

    /**
     * 上传文件
     */
    public void upload() {
        // 从文件选择器或者摄像头中获取
        Uri file1Uri = Uri.fromFile(new File(""));
        Uri file2Uri = Uri.fromFile(new File(""));
        // 创建 service
        HotTopicService hotTopicService = retrofit_rx.create(HotTopicService.class);

        // 创建文件的 part
        MultipartBody.Part part1 = prepareFilePart("viedo", file1Uri);
        MultipartBody.Part part2 = prepareFilePart("thumbnail", file2Uri);

        // 创建请求体
        RequestBody description = createParFromString("hell");

        Call call = hotTopicService.uploadMultipleFiles(description, part1, part2);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }

    public RequestBody createParFromString(String descriptionString) {

        return RequestBody.create(MultipartBody.FORM, descriptionString);
    }

    public MultipartBody.Part prepareFilePart(String parName, Uri fileUri) {
        File file = new File("");
        // 为 file 建立 RequestBody 实例
        RequestBody requestFile = RequestBody.create(MultipartBody.FORM, file);

        return MultipartBody.Part.createFormData(parName, file.getName(), requestFile);
    }


}
