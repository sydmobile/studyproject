package com.syd.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.syd.study.lambda.LambdaActivity;
import com.syd.study.viewstub.ViewStubActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btLambda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private static final String TAG = "MainActivity";

    public void initView() {
        TextView textView = findViewById(R.id.tv);
        textView.setOnClickListener(this);
        btLambda = findViewById(R.id.bt_lambda);
        btLambda.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();


        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                Intent intent = new Intent(this, ViewStubActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_lambda:
                Intent intent1 = new Intent(this, LambdaActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
