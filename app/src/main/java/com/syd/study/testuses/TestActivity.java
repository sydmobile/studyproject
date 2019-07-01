package com.syd.study.testuses;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$d$
 * <p>
 * date: 2019/6/24 19:22
 *
 * @author syd
 * @version 1.0
 */
public class TestActivity extends BaseActivity {
    @BindView(R.id.et_test_data)
    EditText etTestData;
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte b = 1;
                long a = 1 << 7;
                byte c = -128;
                Log.e("===",a+"");
                Log.e("===",(long)(b<<31)+"");
                Log.e("===",(((long)b)<< 31)+"");
                Log.e("===",((b)<< 30)+"");
                Log.e("===",(int)c +"");
                Log.e("===",(c & 0xFF )+"");

            }
        });
    }
}
