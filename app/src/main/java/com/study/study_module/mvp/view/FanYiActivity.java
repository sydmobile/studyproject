package com.study.study_module.mvp.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2019/11/29 10:53
 *
 * @author syd
 * @version 1.0
 */
public class FanYiActivity extends BaseActivity {

    @BindView(R.id.bt_click)
    Button btClick;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_simple;
    }

}
