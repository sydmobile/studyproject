package com.study.study_module.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.study.R;
import com.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：RecyclerView 使用
 * <p>
 * date: 2019/6/10 15:51
 *
 * @author syd
 * @version 1.0
 */
public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        rlv.setLayoutManager(new LinearLayoutManager(this));
//        rlv.setLayoutManager(new GridLayoutManager());
        // spanCount 扩展的数字， 垂直的时候,水平方向有两个
        // 水平的时候，垂直方向有 spanCount 个，垂直方向不会滑动。
        rlv.setLayoutManager(new StaggeredGridLayoutManager(6, OrientationHelper.VERTICAL));

        // 效果和 StaggeredGridLayoutManager 一样，reverseLayout 代表页数置反，比如，一共有两页，如果 reverseLayout 设置
        // 成 true 了，则滑动的时候是要向左边滑动的，默认在第二页。 这种 Manager是不能使用瀑布流的。每个 Item 大小固定了，会取最大 item 作为 item 的大小。
//        rlv.setLayoutManager(new GridLayoutManager(this,6,RecyclerView.VERTICAL,false));



//        rlv.setAdapter(new NormalRecyclerViewAdapter1(this,getResources().getStringArray(R.array.rlv_data)));
//        rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        rlv.addItemDecoration(new CustomItemDecoration(this,DividerItemDecoration.VERTICAL));


        List<String> listDatas = new ArrayList<>();
        for (int i = 0 ;i<30;i++){
            listDatas.add("条目"+i);
        }
//
        WaterpallStaggeredAdapter adapter = new WaterpallStaggeredAdapter(this,listDatas);
        adapter.setmOnItemClickListener(new WaterpallStaggeredAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this,"长按了"+position,Toast.LENGTH_SHORT).show();
            }
        });
        rlv.setAdapter(adapter);
//        rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        rlv.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_recyclerview;
    }

}
