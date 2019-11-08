package com.syd.study.material;

import android.app.Activity;
import android.os.Bundle;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * 说明：AppCompat主题$
 * <p>
 * date: 2019/11/7 10:02
 *
 * @author syd
 * @version 1.0
 */
public class MaterialCompatActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
    }
}
