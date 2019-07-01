package com.syd.study.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syd.study.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：瀑布流适配器
 * <p>
 * date: 2019/6/10 18:33
 *
 * @author syd
 * @version 1.0
 */
public class WaterpallStaggeredAdapter extends RecyclerView.Adapter<WaterpallStaggeredAdapter.WaterpallViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;
    private List<Integer> mHeights;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public WaterpallStaggeredAdapter(Context context, List<String> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }

    }

    @NonNull
    @Override
    public WaterpallViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_rlv,viewGroup,false);
        return new WaterpallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WaterpallViewHolder waterpallViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams= waterpallViewHolder.tv.getLayoutParams();
        layoutParams.height = mHeights.get(i);
        waterpallViewHolder.tv.setLayoutParams(layoutParams);
        waterpallViewHolder.tv.setText(mDatas.get(i));
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener!=null){
            waterpallViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,waterpallViewHolder.getLayoutPosition());
                }
            });

            waterpallViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(v,waterpallViewHolder.getLayoutPosition());

                    removeData(waterpallViewHolder.getLayoutPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null?0:mDatas.size();
    }

    public void addData(int position){
        mDatas.add(position,"insert one");
        mHeights.add((int)(100 + Math.random() * 300));
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class WaterpallViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.cv_item)
        CardView cvItem;

        public WaterpallViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }

}
