package com.syd.study.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：RecyclerView 详解
 *
 * <p>
 * date: 2019/7/2 9:26
 *
 * @author syd
 * @version 1.0
 */
public class RecyclerViewActivityOne extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    List<String> listData;
    @BindView(R.id.tv)
    TextView tv;
    // 瀑布流布局适配器
    WaterfallStaggeredAdapter waterfallStaggeredAdapter;
    RecyclerViewScrollListener recyclerViewScrollListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                waterfallStaggeredAdapter.insetData(3, "insert");
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }

    public void init() {
        // 模拟数据
        initData();

        //region 最基本的使用方式

        // 创建布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rlv.setLayoutManager(linearLayoutManager);
////
////        // 创建适配器
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, listData);
////
////        // 将适配器填充到 RecyclerView 中
////
//        rlv.setAdapter(recyclerViewAdapter);


        //endregion

//        registerForContextMenu(rlv);

        //region 瀑布流demo
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,
//                StaggeredGridLayoutManager.VERTICAL);
//        rlv.setLayoutManager(layoutManager);
//        waterfallStaggeredAdapter =new WaterfallStaggeredAdapter(this, listData);
//        rlv.setAdapter(waterfallStaggeredAdapter);
////

        //endregion

        // 美观方面
        rlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL), 0);
//        rlv.setItemAnimator(new DefaultItemAnimator());


        //region 加载相关

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rlv.setLayoutManager(linearLayoutManager1);
        MyLinearAdapter linearAdapter = new MyLinearAdapter(listData, R.layout.item_head, R.layout.item_footer);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e("handler", "===");
                List<String> list = (List<String>) msg.obj;
                linearAdapter.getList().addAll(list);
                linearAdapter.notifyDataSetChanged();
            }
        };
        rlv.setAdapter(linearAdapter);
        recyclerViewScrollListener = new RecyclerViewScrollListener() {
            @Override
            public void onStart() {
                Log.e("onStart", "==");
                linearAdapter.setFooterView(R.layout.item_footer);
                if (linearAdapter.hasHeader()) {
                    rlv.smoothScrollToPosition(linearAdapter.getItemCount() + 1);
                } else {
                    rlv.smoothScrollToPosition(linearAdapter.getItemCount());
                }
                Log.e("itemCount", linearAdapter.getItemCount() + "");
            }

            @Override
            public void onLoadMore() {
                Log.e("onLoadMore==", "more");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.e("onLoadMore", "more");
                            Thread.sleep(3000);
                            List<String> addList = new ArrayList<>();
                            for (int i = 0; i < 15; i++) {
                                addList.add("添加数" + i);
                            }
                            onFinish(addList);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onFinish(List<String> list) {
                Message message = Message.obtain();
                message.obj = list;
                handler.sendMessage(message);
                recyclerViewScrollListener.setLoadingMore(false);

            }
        };
        rlv.addOnScrollListener(recyclerViewScrollListener);


        //endregion

    }

    private void initData() {
        listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listData.add("item" + i);
        }
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        // 布局填充器
        LayoutInflater layoutInflater;

        private List<String> mDatas;
        private Context context;


        public RecyclerViewAdapter(Context context, List<String> datas) {
            this.context = context;
            layoutInflater = LayoutInflater.from(context);
            if (datas != null) {
                mDatas = new ArrayList<>();
                mDatas.addAll(datas);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? 0 : 1;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 会比可见的 item 多几个（5个），也是在滑动到可见的时候调用，而不是提前就调用。
            // 收到的感觉是 item 可见了，立刻触发了这个方法
            RecyclerView.ViewHolder recyclerViewHolder;

            if (viewType == 0) {
                View view = layoutInflater.inflate(R.layout.item_rlv, parent, false);
                recyclerViewHolder = new RecyclerViewHolder(view, context);
                Log.e("onCreateViewHolder", "toString:" + recyclerViewHolder.toString());
            } else {
                View view = layoutInflater.inflate(R.layout.item_rlv_one, parent, false);
                recyclerViewHolder = new RecyclerViewHolderOne(view);
            }
            return recyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            // 这个在滑动的时候会不断的执行，提前执行一个（前提是 holder 不够用）
            // 比如，当前页面显示 8 个，那么这个方法会执行到第九个（如果 holder 够用的话就不会执行。）
            if (holder instanceof RecyclerViewHolder) {
                ((RecyclerViewHolder) holder).tv.setText(mDatas.get(position));
            } else {

            }

            Log.e("onBindViewHolder", "position:" + position);
        }

        @Override
        public int getItemCount() {
            return mDatas == null ? 0 : mDatas.size();
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.cv_item)
        CardView cvItem;
        Context context;

        RecyclerViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(RecyclerViewHolder.this.context,
//                            RecyclerViewHolder.this.tv.getText() + "" + getLayoutPosition(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    class RecyclerViewHolderOne extends RecyclerView.ViewHolder {

        public RecyclerViewHolderOne(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 模仿瀑布流适配器
     */
    class WaterfallStaggeredAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

        LayoutInflater layoutInflater;
        Context context;
        List<String> mDatas;
        List<Integer> mHeights;

        WaterfallStaggeredAdapter(Context context, List<String> data) {
            this.context = context;
            this.mDatas = data;
            this.layoutInflater = LayoutInflater.from(context);
            mHeights = new ArrayList<>();
            for (String s : data) {
                mHeights.add((int) (Math.random() * 100 + 300));
            }
        }

        public void insetData(int position, String str) {
            mDatas.add(position, str);
            mHeights.add(position, (int) (Math.random() * 100 + 100));
            notifyItemInserted(position);
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            return new RecyclerViewHolder(layoutInflater.inflate(R.layout.item_rlv, parent, false), context);
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            ViewGroup.LayoutParams layoutParams = holder.tv.getLayoutParams();
            layoutParams.height = mHeights.get(position);
            holder.tv.setLayoutParams(layoutParams);
            holder.tv.setText(listData.get(position));

        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

    }

    class MyLinearAdapter extends com.syd.study.recyclerview.RecyclerViewAdapter<String> {

        public MyLinearAdapter(List<String> mDatas) {
            super(mDatas);
        }

        public MyLinearAdapter(List<String> mDatas, int headerViewRes) {
            super(mDatas, headerViewRes);
        }

        public MyLinearAdapter(List<String> mDatas, int headerViewRes, int footerViewRes) {
            super(mDatas, headerViewRes, footerViewRes);
        }

        @Override
        public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {

            return new RecyclerViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rlv, parent, false),
                    parent.getContext());
        }

        @Override
        public void onBindHeaderView(View headerView) {
            // 头部绑定后的操作
        }

        @Override
        public void onBindFooterView(View footerView) {
            // 尾部绑定操作
        }

        @Override
        public void onBindItemView(RecyclerView.ViewHolder holder, String item) {
            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.tv.setText(item);
        }
    }


}
