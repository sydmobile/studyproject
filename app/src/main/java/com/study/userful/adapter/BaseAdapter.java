package com.study.userful.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 说明：RecyclerView BaseAdapter
 * <p>
 * date: 2020/5/28 16:38
 *
 * @author syd
 * @version 1.0
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private List<T> datas = new ArrayList<>();


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        if (position < datas.size()) {
            holder.bindViewHolder(datas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 添加数据并更新
     *
     * @param value item
     */
    public void addItem(T value) {
        if (value != null) {
            datas.add(value);
            notifyItemInserted(datas.size() - 1);
        }
    }

    /**
     * 添加多条数据并更新
     *
     * @param datas 多条数据
     */
    public void addItems(List<T> datas) {
        if (datas != null && datas.size() > 0) {
            int oldSize = this.datas.size();
            this.datas.addAll(datas);
            notifyItemRangeChanged(oldSize, datas.size());
        }
    }

    /**
     * 清空数据
     */
    public void clear() {
        this.datas.clear();
        notifyDataSetChanged();
    }

    /**
     * 移除存在的第一条数据
     *
     * @param value 数据
     */
    public void remove(T value) {
        if (this.datas.contains(value)) {
            int oldPosition = this.datas.indexOf(value);
            this.datas.remove(value);
            notifyItemRemoved(oldPosition);
        }
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

}
