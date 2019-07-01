package com.syd.study.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syd.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：适配器
 * <p>
 * date: 2019/6/10 17:35
 *
 * @author syd
 * @version 1.0
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;

    public NormalRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.rlv_data);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NormalRecyclerViewAdapter.NormalTextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_rlv, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NormalRecyclerViewAdapter.NormalTextViewHolder viewHolder, int i) {
        viewHolder.tv.setText(mTitles[i]);

    }


    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    class NormalTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.cv_item)
        CardView cvItem;

         NormalTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("NormalTextViewHolder","onClick--->position"+getLayoutPosition());
                }
            });
        }


    }

}
