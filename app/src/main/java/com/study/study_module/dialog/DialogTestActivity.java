package com.study.study_module.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.study.R;
import com.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/8/28 15:53
 *
 * @author syd
 * @version 1.0
 */
public class DialogTestActivity extends BaseActivity {

    @BindView(R.id.bt_one)
    Button btOne;
    List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.activity_dialog_test;
    }

    @Override
    public void initView() {
        super.initView();
        btOne.setOnClickListener(v -> {
            DialogFragmentBo dialogFragmentBo = new DialogFragmentBo();
            dialogFragmentBo.show(getSupportFragmentManager(), null);

        });
        mFragments.add(MyFragment.newInstance(0));
        mFragments.add(MyFragment.newInstance(1));
        mFragments.add(MyFragment.newInstance(2));
        mFragments.add(MyFragment.newInstance(3));
        mFragments.add(MyFragment.newInstance(4));
        mFragments.add(MyFragment.newInstance(5));
        TabLayout tb = findViewById(R.id.tb);
        ViewPager vp = findViewById(R.id.vp);
        vp.setAdapter(new FragmentAdapter(getSupportFragmentManager(), 1));
        tb.setupWithViewPager(vp);
//        String[] titles = {"推荐", "新闻", "娱乐", "短视频", "教育学习", "好好学习", "体育"};
//
//        for (String title : titles) {
//            tb.addTab(tb.newTab().setText(title));
//        }
        tb.setTabMode(TabLayout.MODE_SCROLLABLE);
        tb.setTabTextColors(Color.BLACK, Color.RED);
        tb.setSelectedTabIndicatorColor(Color.BLUE);
        tb.setTabIndicatorFullWidth(false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public static class MyFragment extends Fragment {
        private View mView;
        private String[] mTitles = {"推荐", "新闻", "娱乐", "短视频", "教育学习", "好好学习", "体育"};

        public static MyFragment newInstance(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            MyFragment fragment = new MyFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            mView = inflater.inflate(R.layout.material_tablayout_fragment_page, container, false);
            initView();
            return mView;
        }

        private void initView() {
            assert getArguments() != null;
            int tabIndex = getArguments().getInt("position");
            TextView tv = mView.findViewById(R.id.tv);
            tv.setText(mTitles[tabIndex]);
        }
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        private String[] mTitles = {"推荐", "新闻", "娱乐", "短视频", "教育学习", "好好学习", "体育"};

        public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


    }

}
