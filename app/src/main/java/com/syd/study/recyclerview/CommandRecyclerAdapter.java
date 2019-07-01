package com.syd.study.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 说明：RecyclerView 的普通适配器
 * <p>
 * date: 2019/6/24 14:17
 *
 * @author syd
 * @version 1.0
 */
public abstract class CommandRecyclerAdapter<T> extends RecyclerView.Adapter<MyViewHolder> {
    public Context context;
    public int mLayoutId;
    public List<T> mDatas;
    public LayoutInflater mInflater;

    public CommandRecyclerAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return MyViewHolder.get(context, viewGroup, mLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        convert(myViewHolder, mDatas.get(i));
        /*
         * 为 item 设置点击和长按事件监听
         */
        myViewHolder.setOnClickListener(-1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(mDatas.get(myViewHolder.getAdapterPosition()), myViewHolder.getAdapterPosition());
            }
        });

        myViewHolder.setOnLongClickListener(-1, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemLongClick(mDatas.get(myViewHolder.getAdapterPosition()),
                        myViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public void setData(List<T> datas){
        if (datas!=null){
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void addData(int position,T t){
        if (null != mDatas){
            mDatas.add(position,t);
            notifyItemInserted(position);
        }
    }

    public void addDatas(List<T> mDatas){
        if (mDatas!=null){
            this.mDatas.addAll(mDatas);
            notifyDataSetChanged();
        }
    }

    public void removeData(int position){
        if (null !=mDatas && mDatas.size() > position){
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeDatas(List<Integer> positions){
        if (null!= mDatas){
            for (int position:positions){
                if (mDatas.size() > position){
                    mDatas.remove(position);
                    notifyItemRemoved(position);
                }
            }

        }
    }

    /**
     * 重写此方法，将数据绑定到控件上
     *
     * @param holder holder
     * @param t      数据
     */
    public abstract void convert(MyViewHolder holder, T t);

    public abstract void onItemClick(T data, int position);

    public boolean onItemLongClick(T data, int position) {

        return true;
    }

}
