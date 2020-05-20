package com.study.study_module.recyclerview.brvahdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.study.R;
import com.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$d$
 * <p>
 * date: 2019/7/5 10:08
 *
 * @author syd
 * @version 1.0
 */
public class BaseUseActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rlv)
    RecyclerView rlv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listData.add("item" + i);
        }
        QuickAdapter quickAdapter = new QuickAdapter(R.layout.item_rlv, listData);
        rlv.setAdapter(quickAdapter);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter.remove(position);
                Log.e("view", position + "");


            }
        });
        quickAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("onItemLong", position + "");
                return true;
            }
        });
        quickAdapter.openLoadAnimation();
        quickAdapter.addHeaderView(View.inflate(this, R.layout.item_head, null));
//        quickAdapter.addFooterView(View.inflate(this,R.layout.item_footer,null),1, LinearLayout.HORIZONTAL);
        quickAdapter.addFooterView(View.inflate(this, R.layout.item_head, null), 0);
        quickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rlv.postDelayed(() ->
                {
                    Log.e("rlv","one"+listData.size());
                    quickAdapter.addData(listData);
                    quickAdapter.loadMoreComplete();
                }, 1000);
            }
        }, rlv);

        quickAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {

            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_recyclerview;
    }

    class QuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public QuickAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        public QuickAdapter(@Nullable List<String> data) {
            super(data);
        }

        public QuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
//            helper.setText(R.i)
//          helper.setTe
            helper.setText(R.id.tv, item);
        }
    }

    class MySectionEntity extends SectionEntity<Video>{
        private boolean isMore;

        public MySectionEntity(boolean isHeader, String header) {
            super(isHeader, header);
        }
        public MySectionEntity(Video video){
            super(video);

        }
    }

    class Video{

    }



    class SectionAdapter extends BaseSectionQuickAdapter<MySectionEntity,BaseViewHolder>{


        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param layoutResId      The layout resource id of each item.
         * @param sectionHeadResId The section head layout id for each item
         * @param data             A new list is created out of this one to avoid mutable list
         */
        public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySectionEntity> data) {
            super(layoutResId, sectionHeadResId, data);
        }

        @Override
        protected void convertHead(BaseViewHolder helper, MySectionEntity item) {

        }

        @Override
        protected void convert(BaseViewHolder helper, MySectionEntity item) {

        }
    }
}
