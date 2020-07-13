package com.study.study_module.fragment;

import android.widget.FrameLayout;

import com.study.R;
import com.study.base.BaseActivity;
import com.study.study_module.fragment.fragment.FragmentOne;
import com.study.study_module.fragment.fragment.FragmentTwo;

import butterknife.BindView;

/**
 * 说明：$
 * <p>
 * date: 2020/6/2 12:10
 *
 * @author syd
 * @version 1.0
 */
public class FragmentMainActivity extends BaseActivity {
    @BindView(R.id.fl)
    FrameLayout fl;

    @Override
    protected int layoutId() {
        return R.layout.activity_fragmentmain;
    }

    @Override
    public void initView() {
        super.initView();
        FragmentOne fragmentOne = new FragmentOne();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl, fragmentOne)
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl,new FragmentTwo())
                .addToBackStack(null)
                .commit();

    }
}
