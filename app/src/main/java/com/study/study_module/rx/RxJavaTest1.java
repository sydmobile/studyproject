package com.study.study_module.rx;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.study.R;
import com.study.adapter.TestAdapter;
import com.study.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * 说明：$
 * <p>
 * date: 2019/12/16 18:10
 *
 * @author syd
 * @version 1.0
 */
public class RxJavaTest1 extends BaseActivity {
    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.et_params)
    EditText etParams;
    @BindView(R.id.tv_show)
    TextView tvShow;
    String[] command = new String[]{"testThread"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_test_common;
    }

    @Override
    public void initView() {
        super.initView();
        TestAdapter adapter = new TestAdapter(this, command);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener((parent, view, position, id) -> {
            switch (command[position]) {
                case "testThread":
                    toast("test");
                    break;
            }

        });
    }


}
