package com.syd.study.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.syd.study.base.callback.PermissionListener;

import java.lang.ref.WeakReference;
/**
 * 说明：Activity 基础类
 * <p>
 * date: 2019/5/10 17:07
 *
 * @author syd
 * @version 1.0
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    public String TAG;
    /** 判断当前 Activity 是否在前台 */
    protected boolean isActive = false;

    /** 当前 Activity 实例 */
    protected Activity activity = null;

    /** Activity 中显示加载等待的控件 */
    protected ProgressBar loading = null;

    /** Activity 中由于服务器异常导致加载失败显示的布局 */
    private View loadErrorView = null;

    /** Activity 中由于网络异常导致加载失败显示的布局 */
    private View badNetworkView = null;

    /** Activity 中当前界面上没有任何内容时展现的布局 */
    private View noContentView = null;

    private WeakReference weakReference = null;

    private Toolbar toolbar = null;

    private ProgressDialog progressDialog = null;

    private PermissionListener mListener = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        TAG = this.getClass().getSimpleName();
    }

    public void init(){
        initData();
        initView();
        initListener();
        initNetData();
    }

    public void initData(){

    }

    public void initView(){

    }

    public void initListener(){

    }

    public void initNetData(){

    }




}
