package com.syd.study.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
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
public abstract class RecyclerViewAdapter<T extends String> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


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

    public RecyclerViewAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public RecyclerViewAdapter(List<T> mDatas, int headerViewRes) {
        this.mDatas = mDatas;
        setHeaderView(headerViewRes);

    }

    public RecyclerViewAdapter(List<T> mDatas, int headerViewRes, int footerViewRes) {
        this.mDatas = mDatas;
        setHeaderView(headerViewRes);
        setFooterView(footerViewRes);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (hasHeader() && viewType == ItemType.TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(getHeaderViewRes(), parent, false);
            return new HeaderViewHolder(view);
        } else if (hasFooter() && viewType == ItemType.TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(getFooterViewRes(), parent, false);
            return new FooterViewHolder(view);
        } else {
            return onCreateHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ItemType.TYPE_NORMAL) {
            onBindItemView(holder, getItemByPosition(position));
        } else if (getItemViewType(position) == ItemType.TYPE_HEADER) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            onBindHeaderView(holder.itemView);
        } else if (getItemViewType(position) == ItemType.TYPE_FOOTER) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            onBindFooterView(footerViewHolder.itemView);
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        } else {
            if (mDatas.size() == 0) {
                return 0;
            } else {
                int count = 0;
                count += (hasHeader() ? 1 : 0);
                count += (hasFooter() ? 1 : 0);
                count += mDatas.size();
                return count;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int size = mDatas.size();
        if (hasHeader()) {
            if (position == 0) {
                return ItemType.TYPE_HEADER;
            } else {
                if (position == size + 1) {
                    return ItemType.TYPE_FOOTER;
                } else {
                    return ItemType.TYPE_NORMAL;
                }
            }
        } else {
            if (position == size) {
                return ItemType.TYPE_FOOTER;
            } else {
                return ItemType.TYPE_NORMAL;
            }
        }
    }

    /**
     * 返回 item 数据，如果有 header 的话 position 必须大于等于 1
     *
     * @param position 位置
     * @return item 数据
     */
    protected T getItemByPosition(int position) {
        if (hasHeader()) {

            return mDatas.get(position - 1);
        } else {
            return mDatas.get(position);
        }
    }

    public List<T> getList() {

        return mDatas;
    }

    public void setList(List<T> list) {
        this.mDatas = list;
    }

    /**
     * 判断 position 的 item
     *
     * @param position 判断的位置
     * @return 是否是头布局
     */
    public boolean isHeader(int position) {
        return hasHeader() && position == 0;
    }

    /**
     * 判断 position 位置的 item 是否是 footer
     *
     * @param position 位置
     * @return 是否
     */
    public boolean isFooter(int position) {
        if (hasHeader) {
            return hasFooter() && position == mDatas.size() + 1;
        } else {
            return hasFooter() && position == mDatas.size();
        }
    }

    public boolean hasHeader() {
        return hasHeader;

    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public int getHeaderViewRes() {
        return headerViewRes;
    }

    public int getFooterViewRes() {
        return footerViewRes;
    }

    /**
     * 设置头布局文件
     *
     * @param headerViewRes 头布局资源
     */
    public void setHeaderView(int headerViewRes) {
        if (headerViewRes != 0) {
            if (hasHeader()) {
                this.headerViewRes = headerViewRes;
                notifyDataSetChanged();
            } else {
                this.headerViewRes = headerViewRes;
                this.hasHeader = true;
                notifyItemInserted(0);
            }
        } else {
            // 如果传入的资源不正确，则设置为 没有 header
            if (hasHeader()) {
                this.hasHeader = false;
                notifyItemRemoved(0);
            }
        }
    }

    /**
     * 设置脚布局
     *
     * @param footerViewRes 脚布局资源
     */
    public void setFooterView(int footerViewRes) {
        if (footerViewRes != 0) {
            if (hasFooter()) {
                this.footerViewRes = footerViewRes;
                notifyDataSetChanged();
            } else {
                this.footerViewRes = footerViewRes;
                this.hasFooter = true;
                if (hasHeader()) {
                    notifyItemInserted(mDatas.size() + 1);
                } else {

                    notifyItemInserted(mDatas.size());
                }
            }
        } else {
            if (hasFooter()) {
                this.hasFooter = false;
                if (hasHeader()) {
                    notifyItemRemoved(mDatas.size() + 1);
                } else {

                    notifyItemRemoved(mDatas.size());
                }
            }
        }
    }

    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType);

    public abstract void onBindHeaderView(View headerView);

    public abstract void onBindFooterView(View footerView);

    public abstract void onBindItemView(RecyclerView.ViewHolder holder, T item);

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
