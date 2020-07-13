package com.study.userful.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * 说明：BaseViewHolder
 * <p>
 * date: 2020/5/28 16:38
 *
 * @author syd
 * @version 1.0
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(int layout, ViewGroup parent, Context context) {
        super(LayoutInflater.from(context).inflate(layout, parent, false));
        ButterKnife.bind(this, itemView);
    }


    public abstract void bindViewHolder(T value);


}
