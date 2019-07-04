package com.syd.study.recyclerview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：ListView Activity
 * <p>
 * date: 2019/6/17 17:48
 *
 * @author syd
 * @version 1.0
 */
public  class ListViewActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    // https://www.jianshu.com/p/4e8e4fd13cf7
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }

    public void init(){
        List<String> list = new ArrayList<>();
        for (int i= 0 ;i<30;i++){
            list.add("string"+i);
        }
        registerForContextMenu(lv);
        // 创建 ArrayAdapter 对象
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
//        AdapterCommon<String> adapterCommon = new AdapterCommon<>(list, this, new AdapterCommon.CallBack() {
//            @Override
//            public View getView(int position, View converView, ViewGroup parent, AdapterCommon adapterCommon) {
//                converView =
//
//                return null;
//            }
//        });
//        lv.setAdapter(adapter);

//        String [] name = new String[]{"wwww","aaa","wwwww","rrrr"};
//        String [] address = new String[]{"北京","上海","广东","深圳"};
//        int [] test = new int[]{22,33,44,55};
//        int [] picture = new int[]{R.drawable.button,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher,R.mipmap.ic_launcher_round};
//        // 定义一个 HashMap 构成的列表以
//        ArrayList<HashMap<String,Object>> listItem = new ArrayList<>();
//        for (int i = 0;i<name.length;i++){
//            HashMap<String,Object> hashMap = new HashMap<>();
//            hashMap.put("name",name[i]);
//            hashMap.put("address",address[i]);
//            hashMap.put("test",test[i]);
//            hashMap.put("picture",picture[i]);
//            listItem.add(hashMap);
//        }
//        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,listItem,R.layout.item_simple_adapter,new String[]{"name","address","test","picture"},new int[]{R.id.tv_one,R.id.tv_two,R.id.tv_three,R.id.tv_four});
//        lv.setAdapter(mSimpleAdapter);
        // 需要布局和数据一一对应，在创建适配器的填入参数的时候比较繁琐

        lv.setAdapter(new MyAdapter(this,list));

        // 页面可以看到几个 item ，第一次就执行 getView多少次。当再出现一个 item 的时候再调用一次 getView
        // contentView 的个数为页面可见的个数 +1 。然后不可见的 item 放入回收栈中，等待下一次回收使用。


    }

    class MyAdapter extends BaseAdapter{
        // 用来填充布局
        private LayoutInflater mInflater;
        private Context context;
        private List<String> listItem;
        // 构造方法
        public MyAdapter(Context context,List<String> listItem){
            this.context = context;
            this.listItem = listItem;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return listItem.size();
        }

        @Override
        public Object getItem(int position) {
            return listItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = mInflater.inflate(R.layout.item_rlv,parent,false);
            }
            Log.e("getView","convertView:"+convertView.toString());
            return convertView;
        }
    }

//    class MyViewHolder


}
