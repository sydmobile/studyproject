package com.syd.study.viewstub;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

public class ViewStubActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstub);
        initView();
    }

    public void initView(){
        TextView textView = findViewById(R.id.tv);
        final ViewStub viewStub = findViewById(R.id.vs);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStub.inflate();

            }
        });
    }
}
