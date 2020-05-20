package com.study.study_module.recyclerview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.R;
import com.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$d$
 * <p>
 * date: 2019/8/29 10:44
 *
 * @author syd
 * @version 1.0
 */
public class PulmListViewActivity extends BaseActivity {
    @BindView(R.id.tv_create_menu)
    TextView tvCreateMenu;
    @BindView(R.id.lv)
    PulmListView lv;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            lv.onFinishLoading(false,getData("加载数据"),false);

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_pulmlist;
    }



    @Override
    public void initView() {
        super.initView();
        MyAdapter myAdapter = new MyAdapter();
        lv.setmOnPullUpLoadMoreListener(new PulmListView.OnPullUpLoadMoreListener() {
            @Override
            public void onPullUpLoadMore() {

                handler.sendEmptyMessageDelayed(1,2000);
            }
        });
        lv.setAdapter(myAdapter);

    }


    public List<String> getData(String content) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(content + Math.random());
        }
        return list;
    }

    class MyAdapter extends PulmListView.PulmBaseAdapter<String> {

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public String getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder myViewHolder;
            if (convertView == null) {
                convertView = View.inflate(PulmListViewActivity.this, R.layout.item_simple_adapter, null);
                myViewHolder = new MyViewHolder(convertView);
                convertView.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.tvOne.setText(getItem(position));
            return convertView;
        }
    }

    class MyViewHolder {
        @BindView(R.id.tv_one)
        TextView tvOne;
        @BindView(R.id.tv_two)
        TextView tvTwo;
        @BindView(R.id.tv_three)
        TextView tvThree;
        @BindView(R.id.tv_four)
        ImageView tvFour;

        public MyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
