package com.syd.study.mvc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;
import com.syd.study.mvc.callback.Callback1;
import com.syd.study.mvc.model.BaseModel;
import com.syd.study.mvc.model.SampleModel;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 13:59
 *
 * @author syd
 * @version 1.0
 */
public class SimpleActivity extends BaseActivity {

    @BindView(R.id.bt_click)
    Button btClick;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    SampleModel sampleModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
        sampleModel = new SampleModel();
        // View  ---> Controller
        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("111");
            }
        });
    }

    /**
     * 操控 Model 层 Controller---> Model
     * @param uid
     */
    private void getUserInfo(String uid){
        sampleModel.getUserInfo(uid, new Callback1<SampleModel.UserInfo>() {
            @Override
            public void onCallBack(SampleModel.UserInfo userInfo) {
                setDataToView(userInfo);
            }
        });
    }

    /**
     * 属于 View 层
     * @param userInfo
     */
    private void setDataToView(SampleModel.UserInfo userInfo){
        tvAge.setText(userInfo.getAge());
        tvName.setText(userInfo.getName());
    }
}
