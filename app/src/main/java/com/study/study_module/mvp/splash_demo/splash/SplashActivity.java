package com.study.study_module.mvp.splash_demo.splash;

import android.content.Intent;

import com.study.MainActivity;
import com.study.study_module.mvp.splash_demo.AbsBaseActivity;

/**
 * 说明：$
 * <p>
 * date: 2020/4/14 16:12
 *
 * @author syd
 * @version 1.0
 */
public class SplashActivity extends AbsBaseActivity implements SplashContract.View {

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
