package com.syd.study.loadmore_refresh;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：刷新页面
 * <p>
 * date: 2019/8/29 18:01
 *
 * @author syd
 * @version 1.0
 */
public class RefreshActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    List<String> list;
    @BindView(R.id.refresh)
    MyCustomRefreshView refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void initView() {
        super.initView();
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("这是一条数据" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
//        refresh.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
//            @Override
//            public void onRefresh() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                refresh.finishRefreshing();
//            }
//        },0);

    }
}
