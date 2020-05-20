package com.study.study_module.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：RecyclerViewAdapter
 * <p>
 * date: 2019/6/21 17:38
 *
 * @author syd
 * @version 1.0
 */
public class NormalRecyclerViewAdapter1 extends RecyclerView.Adapter<NormalRecyclerViewAdapter1.MyViewHolder> {

    private LayoutInflater mLayoutInflater;
    private String[] list;

    NormalRecyclerViewAdapter1(Context context, String[] list) {

        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @NonNull
    @Override
    public NormalRecyclerViewAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MyViewHolder(mLayoutInflater.inflate(R.layout.item_rlv, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NormalRecyclerViewAdapter1.MyViewHolder viewHolder, int i) {

        viewHolder.tv.setText(list[i]);

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.cv_item)
        CardView cvItem;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
