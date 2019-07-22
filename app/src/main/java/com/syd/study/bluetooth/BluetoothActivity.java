package com.syd.study.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：普通蓝牙设备
 * <p>
 * date: 2019/6/19 10:19
 *
 * @author syd
 * @version 1.0
 */
public class BluetoothActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bt_open)
    Button btOpen;
    @BindView(R.id.bt_bond_devices)
    Button btBondDevices;
    @BindView(R.id.bt_discovery)
    Button btDiscovery;
    @BindView(R.id.lv)
    ListView lv;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);
        init();
    }

    public  void init() {
        // 初始化蓝牙
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "此设备不支持蓝牙", Toast.LENGTH_SHORT).show();
        }
        // 设置监听
        btOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 打开蓝牙设备
            case R.id.bt_open:
                // 蓝牙是否开启
                if (bluetoothAdapter.isEnabled()){
                }


        }
    }
}
