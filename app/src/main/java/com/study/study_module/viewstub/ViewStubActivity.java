package com.study.study_module.viewstub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;

import androidx.annotation.Nullable;

public class ViewStubActivity extends BaseActivity {

    // viewStub 指定内容
    private TextView tvViewStubContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_viewstub;
    }


    @SuppressLint("SetTextI18n")
    public void initView() {
        TextView textView = findViewById(R.id.tv);
        final ViewStub viewStub = findViewById(R.id.vs);
        textView.setOnClickListener(v -> {
            View view = viewStub.inflate();
            tvViewStubContent = view.findViewById(R.id.tv_common);
            tvViewStubContent.setText("ViewStub must have a non-null ViewGroup viewParent");
        });
    }
}
