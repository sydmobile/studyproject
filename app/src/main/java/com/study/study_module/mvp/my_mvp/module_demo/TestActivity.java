package com.study.study_module.mvp.my_mvp.module_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.study.R;
import com.study.study_module.mvp.my_mvp.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2020/4/14 18:29
 *
 * @author syd
 * @version 1.0
 */
public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.View {


    @BindView(R.id.et_test_data)
    EditText etTestData;
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        presenter = new TestPresenter(this);
    }


    @Override
    public void updateUI1(String content) {
        etTestData.setText(content);
    }

    @Override
    public void updateUI2() {
        tvTest.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateUI3() {

    }
}
