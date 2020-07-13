package com.study.study_module.material;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

import com.study.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 说明：AppCompat主题$
 * <p>
 * date: 2019/11/7 10:02
 *
 * @author syd
 * @version 1.0
 */
public class MaterialCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
    //如果实现类或子类的范围缩小的话，当调用接口的方法时，其实现类的方法就无法访问，这样其实现类就没有任何意义！抽象类的继承也是如此！！
    }


}
