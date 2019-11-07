package com.syd.study.observer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：设计模式
 * <p>
 * date: 2019/10/10 16:25
 *
 * @author syd
 * @version 1.0
 */
public class DesignModeActivity extends BaseActivity {

    @BindView(R.id.bt_change)
    Button btChange;
    @BindView(R.id.et_price)
    EditText etPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void initView() {
        super.initView();
        // 房子 属于被观察者
        House house = new House(10000);
        // 创建观察者
        People people = new People("张三");
        People people1 = new People("李四");
        People people2 = new People("王五");
        // 注册观察者
        house.addObserver(people);
        house.addObserver(people1);
        house.addObserver(people2);

        btChange.setOnClickListener(v -> {
            float price = Float.parseFloat(etPrice.getText().toString());
            house.updatePrice(price);
        });

    }
}
