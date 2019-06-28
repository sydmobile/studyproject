package com.syd.study.recyclerview;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

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
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        rlv.setLayoutManager(new LinearLayoutManager(this));
//        rlv.setLayoutManager(new GridLayoutManager());
        // spanCount 扩展的数字， 垂直的时候,水平方向有两个
        // 水平的时候，垂直方向有 spanCount 个，垂直方向不会滑动。

//        rlv.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));
        rlv.setAdapter(new NormalRecyclerViewAdapter1(this,getResources().getStringArray(R.array.rlv_data)));
//        rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rlv.addItemDecoration(new CustomItemDecoration(this,DividerItemDecoration.VERTICAL));
//        List<String> listDatas = new ArrayList<>();
//        for (int i = 0 ;i<30;i++){
//            listDatas.add("条目"+i);
//        }
//
//        WaterpallStaggeredAdapter adapter = new WaterpallStaggeredAdapter(this,listDatas);
//        adapter.setmOnItemClickListener(new WaterpallStaggeredAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(RecyclerViewActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Toast.makeText(RecyclerViewActivity.this,"长按了"+position,Toast.LENGTH_SHORT).show();
//            }
//        });
//        rlv.setAdapter(adapter);
//        rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
    }
}
