package com.syd.study.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 说明：演示 加载第二页效果
 * <p>
 * date: 2019/7/3 18:33
 *
 * @author syd
 * @version 1.0
 */
public class RecyclerViewAdapter<T extends String> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    // 数据源
    private List<T> mDatas = null;
    // 头部 layout 资源 id
    private int headerViewRes;
    // 尾部 layout 资源 id
    private int footerViewRes;
    // 当前是否有头
    private boolean hasHeader = false;
    // 当前是否有尾
    private boolean hasFooter = false;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public List<T> getList() {

        return mDatas;
    }

    public void setList(List<T> list){
        this.mDatas = list;
    }

    /**
     * 判断 position 的 item
     * @param position
     * @return
     */
    public boolean isHeader(int position){
        return hasHeader && position == 0;
    }

    /**
     * 不同的布局类型
     */
    public interface ItemType {
        // 头布局
        int TYPE_HEADER = 0;
        // 加载更多布局
        int TYPE_FOOTER = 1;
        // 普通布局
        int TYPE_NORMAL = 2;
    }

    /**
     * 头部 viewHolder
     */
    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
