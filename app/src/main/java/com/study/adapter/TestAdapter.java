package com.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.study.R;

/**
 * 说明：用于测试布局使用的 adapter
 * <p>
 * date: 2020/5/11 10:09
 *
 * @author syd
 * @version 1.0
 */
public class TestAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private String[] commands;

    public TestAdapter(Context context, String[] commands) {
        layoutInflater = LayoutInflater.from(context);
        this.commands = commands;
    }

    @Override
    public int getCount() {
        return commands == null ? 0 : commands.length;
    }

    @Override
    public String getItem(int position) {
        return commands[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_button, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.btCommand.setText(commands[position]);
        return convertView;

    }

    static class ViewHolder {
        private TextView btCommand;

        ViewHolder(View view) {
            btCommand = view.findViewById(R.id.bt);
        }
    }
}
