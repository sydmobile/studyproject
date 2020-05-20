package com.study.study_module.lambda;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.study.R;
import com.study.base.BaseActivity;

public class LambdaActivity extends BaseActivity implements View.OnClickListener {

    private Button btListener;
    private Button btTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_lambda;
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
