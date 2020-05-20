package com.study.userful.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义的一般公用适配器
 * Created by sunyidong on 2016/8/15.
 */
public class AdapterCommon<T> extends BaseAdapter {
    public List<T> data;
    public Context context;
    private CallBack callBack;

    /**
     * @param data     数据
     * @param context  上下文
     * @param callBack 回调
     */
    public AdapterCommon(List<T> data, Context context, CallBack callBack) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.context = context;
        this.callBack = callBack;
    }

    /**
     * 如果 参数不为空的时候更新
     *
     * @param data 数据
     */
    public void updateData(List<T> data) {
        if (data != null && data.size() > 0) {

            this.data = data;
            notifyDataSetChanged();
        }
    }

    /**
     * 更新列表，不论 data 是否有值
     *
     * @param data 数据
     */
    public void updateDateNoLimit(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
//        Matcher matcher;
//        matcher.groupCount()
    }

    /**
     * 增加数据
     *
     * @param data 数据
     */
    @SuppressWarnings("unused")
    public void addData(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 通知更新列表
     */
    @SuppressWarnings("unused")
    public void updateData() {

        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = callBack.getView(position, convertView, parent, this);
        return convertView;
    }

    public interface CallBack {
        View getView(int position, View converView, ViewGroup parent, AdapterCommon adapterCommon);
    }

}
