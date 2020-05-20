package com.study.study_module.material;

import android.app.Activity;
import android.os.Bundle;

import com.study.R;

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
