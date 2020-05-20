package com.study.study_module.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 说明：ViewHolder
 * <p>
 * date: 2019/6/24 13:50
 *
 * @author syd
 * @version 1.0
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    // 用于维护 item 中子控件的引用
    private SparseArray<View> mViews;
    private View mContentView;
    private Context mContext;

    public MyViewHolder(@NonNull View itemView, Context context, ViewGroup parent) {
        super(itemView);
        mContentView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 根据 Item 布局 Id 创建一个 ViewHolder，Adapter 中调用
     *
     * @param context  上下文
     * @param parent   ViewGroup
     * @param layoutId item id
     * @return ViewHolder
     */
    public static MyViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);

        return new MyViewHolder(itemView, context, parent);
    }

    /**
     * 通过 ViewId 获取控件
     * @param viewId viewId
     * @param <T> view
     * @return view
     */
    @SuppressWarnings("unchecked")
    private <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mContentView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    /**
     * 为 item 或者指定控件设置监听事件
     * @param viewId 要设置监听事件的 ViewId，-1 为 item
     * @param listener 监听对象
     * @return MyViewHolder
     */
    public MyViewHolder setOnClickListener(int viewId, View.OnClickListener listener){
        if (viewId == -1){
            mContentView.setOnClickListener(listener);
        }else {
            View view = getView(viewId);
            view.setOnClickListener(listener);
        }
        return this;
    }
    /**
     * 为 item 或者指定控件设置长按监听事件
     * @param viewId 要设置监听事件的 ViewId，-1 为 item
     * @param listener 监听对象
     * @return MyViewHolder
     */
    public MyViewHolder setOnLongClickListener(int viewId,View.OnLongClickListener listener){
        if (viewId == -1){
            mContentView.setOnLongClickListener(listener);
        }else {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
        }
        return this;
    }

    //region 这里的方法用于做数据的绑定等等，可以根据项目需要，自行添加

    public MyViewHolder setVisible(int viewId,int visible){
        getView(viewId).setVisibility(visible);
        return this;
    }

    public MyViewHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public MyViewHolder setTextColor(int viewId,int color){
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }

    //endregion


}
