package com.syd.study.file;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import androidx.annotation.Nullable;

/**
 * 说明：进行读写操作的
 * <p>
 * date: 2019/8/8 17:29
 *
 * @author syd
 * @version 1.0
 */
public class FileActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void raw(){
        InputStream is = getResources().openRawResource(R.raw.shinei);

    }

    public void asset(){
        try {
            InputStream is = getResources().getAssets().open("ic_launcher_web.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void  dataField(){
        try {
            FileOutputStream fo = openFileOutput("msyt",MODE_PRIVATE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sdcard(){
        // 1.申请权限
        // 2.检查内存状态
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){

        }
    }
}
