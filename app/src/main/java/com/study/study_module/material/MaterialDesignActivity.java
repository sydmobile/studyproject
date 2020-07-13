package com.study.study_module.material;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.study.R;
import com.study.base.BaseActivity;
import com.study.entity.WXArticle;
import com.study.entity.WXArticleListEntity;
import com.study.study_module.retrofit.RetrofitServiceManager;
import com.study.userful.adapter.BaseAdapter;
import com.study.userful.adapter.BaseViewHolder;
import com.study.userful.util.L;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.dynamicbox.DynamicBox;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 说明：MaterialDesign 控件使用
 * <p>
 * date: 2020/5/22 13:51
 *
 * @author syd
 * @version 1.0
 */
public class MaterialDesignActivity extends BaseActivity {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    //    @BindView(R.id.fab)
//    FloatingActionButton fab;
//    @BindView(R.id.rlv)
//    RecyclerView rlv;
////    @BindView(R.id.ctl)
////    CollapsingToolbarLayout ctl;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    ApiServer apiServer;
    RlvAdapter rlvAdapter = new RlvAdapter();
    DynamicBox mBox;
    int currentPage = 1;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private CollapsingToolbarLayoutState state;


    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_design2;
    }

    @Override
    public void initView() {
        super.initView();
        mBox = new DynamicBox(this, rlv);
        apiServer = RetrofitServiceManager.getInstance().create(ApiServer.class);
        rlv.setAdapter(rlvAdapter);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        srl.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvAdapter.clear();
                requestData(currentPage++);
            }
        });
//        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                L.e(appBarLayout.getTotalScrollRange() + "   "+verticalOffset);
//
//                if (verticalOffset == 0) {
//                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
//                        state = CollapsingToolbarLayoutState.EXPANDED;
//                        ctl.setTitle("EXPANDED");
//                        tv.setVisibility(View.GONE);
//                    }
//                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
//                        tv.setVisibility(View.VISIBLE);
//                        ctl.setTitle("");
//                        ctl.setTitle("");
//
//                        state = CollapsingToolbarLayoutState.COLLAPSED;
//                    }
//                } else {
//                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
//                        ctl.setTitle("中间");
//                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
//                    }
//                }
//            }
//        });
        ctl.setExpandedTitleGravity(Gravity.CENTER);
        ctl.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
        ctl.setTitle("哈哈 ");
        mBox.showLoadingLayout();
        requestData(currentPage);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolmenu,menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    public void requestData(int page) {
        Observable<WXArticleListEntity> observable = apiServer.getList(page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wxArticleListEntity -> {
                            rlvAdapter.addItems(wxArticleListEntity.getData().getDatas());
                            mBox.hideAll();
                            if (srl.isRefreshing()) {
                                srl.setRefreshing(false);
                            }
                        },
                        error -> {
                            mBox.hideAll();
                            mBox.showExceptionLayout();
                            Log.e("error", error.getMessage());
                        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class RlvAdapter extends BaseAdapter<WXArticle> {


        @NonNull
        @Override
        public BaseViewHolder<WXArticle> onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
            RlvViewHolder viewHolder = new RlvViewHolder(parent);
            viewHolder.itemView.setOnClickListener(view -> {
                Toast.makeText(MaterialDesignActivity.this,
                        getDatas().get(viewHolder.getAdapterPosition()).getTitle(),
                        Toast.LENGTH_SHORT)
                        .show();
            });
            return viewHolder;
        }
    }


    class RlvViewHolder extends BaseViewHolder<WXArticle> {

        @BindView(R.id.tv_content)
        TextView tvContent;

        RlvViewHolder(@NonNull ViewGroup parent) {
            super(R.layout.item_rlv_simple, parent, MaterialDesignActivity.this);


        }

        @Override
        public void bindViewHolder(WXArticle value) {
            tvContent.setText(value.getTitle());
        }
    }
}
