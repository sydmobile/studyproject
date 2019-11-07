package com.syd.study.recyclerview;

import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 说明：$d$
 * <p>
 * date: 2019/7/3 15:14
 *
 * @author syd
 * @version 1.0
 */
public abstract class RecyclerViewScrollListener extends OnScrollListener implements OnLoadMoreListener<String> {
    // https://blog.csdn.net/cym492224103/column/info/baserecyclerview

    // https://blog.csdn.net/wangkeke1860/article/details/51577158
    protected layoutManagerType mLayoutManagerType;
    private boolean mIsLoadingMore = false;
    private int[] lastPositions;
    private int lastVisibleItemPosition;
    private int currentScrollState = 0;

    public boolean ismIsLoadingMore() {
        return mIsLoadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        mIsLoadingMore = loadingMore;
    }
    // 滑动的时候调用
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Log.e("onScrolled", "dx:" + dx + "===" + "dy:" + dy);
        // 指定 mLayoutManager 的类型
        if (mLayoutManagerType == null) {
            if (layoutManager instanceof GridLayoutManager) {
                mLayoutManagerType = layoutManagerType.GRID_LAYOUT;
            } else if (layoutManager instanceof LinearLayoutManager) {
                mLayoutManagerType = layoutManagerType.LINEAR_LAYOUT;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                mLayoutManagerType = layoutManagerType.STAGGERED_GRID_LAYOUT;
            }
        }
        // 找到当前显示的所有 item 的最后一项
        switch (mLayoutManagerType) {
            case GRID_LAYOUT:
                assert layoutManager != null;
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case LINEAR_LAYOUT:
                assert layoutManager != null;
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

                break;
            case STAGGERED_GRID_LAYOUT:
                StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
        }
        Log.e("lastVisibleItemPosition","last:"+lastVisibleItemPosition);
    }
    // 滑动状态改变的时候调用
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Log.e("onScrollStateChanged","state:"+newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        Log.e("record","visibleItemCount:"+visibleItemCount+"totalItemCount:"+totalItemCount);
        if (visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == totalItemCount-1){
            Log.e("ismIsLoadingMore",ismIsLoadingMore()+"");
            if (!ismIsLoadingMore()){
                mIsLoadingMore = true;
                onStart();
                onLoadMore();
            }
        }


    }
    // 判断是否到底的方法
    // findLastVisibleItemPosition = getItemCount -1
    // findFirstVisibleItemPosition + visibleItemCount(getChildCount) = totalItemCount
    // recyclerView.canScrollVertically(1)

    public int findMax(int[] lastPosition) {
        int max = lastPosition[0];
        for (int value : lastPosition) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public enum layoutManagerType {
        LINEAR_LAYOUT,
        GRID_LAYOUT,
        STAGGERED_GRID_LAYOUT
    }


}

interface OnLoadMoreListener<T>{

    /**
     * 加载更多前回调
     */
    void onStart();

    /**
     * 加载更多
     */
    void onLoadMore();

    /**
     * 由于 onLoadMore 可能是异步加载，所以 onFinish 需要手动调用，
     * 完成数据的刷新，隐藏 Footer 等
     * @param list onLoadMore 中返回的数据
     */
    void onFinish(List<T> list);
}
