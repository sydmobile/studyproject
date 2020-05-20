package com.study.study_module.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：实现 上拉加载更多
 * <p>
 * date: 2019/8/28 14:06
 *
 * @author syd
 * @version 1.0
 */
public class PulmListView extends ListView {

    private UserOnScrollListener mUserOnScrollListener;
    private OnPullUpLoadMoreListener mOnPullUpLoadMoreListener;
    // 是否正在加载
    boolean mIsLoading;
    // 数据是否全部加载完毕
    boolean mIsPageFinished;
    // 加载更多 View
    View mLoadMoreView;
    Context context;

    public PulmListView(Context context) {
        this(context, null);
    }

    public PulmListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    public OnPullUpLoadMoreListener getmOnPullUpLoadMoreListener() {
        return mOnPullUpLoadMoreListener;
    }

    public void setmOnPullUpLoadMoreListener(OnPullUpLoadMoreListener mOnPullUpLoadMoreListener) {
        this.mOnPullUpLoadMoreListener = mOnPullUpLoadMoreListener;
    }

    public void init() {
        mLoadMoreView = View.inflate(context, R.layout.item_footer, null);
        mIsLoading = false;
        mIsPageFinished = false;
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 调用用户设置的 onScrollListener
                if (mUserOnScrollListener != null) {
                    mUserOnScrollListener.onScrollStateChanged(view, scrollState);
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                // 调用用户设置的 onScroll
                if (mUserOnScrollListener != null) {
                    mUserOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                            totalItemCount);
                }
                int lastVisibleItem = firstVisibleItem + visibleItemCount;

                // 判断 是否加载下一页
                // 当前处于 ListView 尾部
                // 并且有更多的数据需要加载、没有处于加载中
                if (!mIsLoading && !mIsPageFinished && totalItemCount == lastVisibleItem) {
                    if (mOnPullUpLoadMoreListener != null) {
                        //将正在加载更多状态设置为 true
                        mIsLoading = true;
                        // 展示加载更多视图
                        showLoadMoreView();
                        mOnPullUpLoadMoreListener.onPullUpLoadMore();
                    }
                }


            }
        });

    }

    public void showLoadMoreView() {
        removeFooterView(mLoadMoreView);
        addFooterView(mLoadMoreView);
    }

    @SuppressWarnings("unchecked")
    public void onFinishLoading(boolean isPageFinished, List<?> newItems, boolean isFirstLoad) {
        mIsLoading = false;
        setIsPageFinished(isPageFinished);
        if (newItems != null && newItems.size() > 0) {
            PulmBaseAdapter pulmBaseAdapter =
                    (PulmBaseAdapter) (((HeaderViewListAdapter) getAdapter()).getWrappedAdapter());

            pulmBaseAdapter.addMoreItems(newItems, isFirstLoad);
        }


    }

    public void setIsPageFinished(boolean isPageFinished) {
        mIsPageFinished = isPageFinished;
        removeFooterView(mLoadMoreView);
    }


    interface UserOnScrollListener {
        void onScrollStateChanged(AbsListView view, int scrollState);

        void onScroll(AbsListView view, int fistVisibleItem, int visibleItemCount,
                      int totalItemCount);

    }

    interface OnPullUpLoadMoreListener {

        void onPullUpLoadMore();
    }

    static abstract class PulmBaseAdapter<T> extends BaseAdapter {
        protected List<T> items;

        public PulmBaseAdapter() {
            this.items = new ArrayList<>();
        }

        public PulmBaseAdapter(List<T> list) {
            this.items = list;
        }

        public void addMoreItems(List<T> newItems, boolean isFirstLoad) {
            if (isFirstLoad) {
                this.items.clear();
            }
            this.items.addAll(newItems);
            notifyDataSetChanged();
        }

        public void removeAllItems() {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

}


