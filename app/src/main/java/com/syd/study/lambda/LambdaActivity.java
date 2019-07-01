package com.syd.study.lambda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

public class LambdaActivity extends BaseActivity implements View.OnClickListener {

    private Button btListener;
    private Button btTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        init();
    }

    public void init(){
        btListener = findViewById(R.id.bt_listener);
        btTest = findViewById(R.id.bt_test);
        btListener.setOnClickListener(v -> {
            MyListener myListener = (c,b)->{
                Log.e("mm",c+b);
            };
            myListener.get("Hello Lambda",2);

        });
        btTest.setOnClickListener((v)->{
            Toast.makeText(this,"Lambda",Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onClick(View v) {


    }

    interface  MyListener{
        void get(String a,int b);
    }
}
