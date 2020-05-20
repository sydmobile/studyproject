package com.study.study_module.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.study.R;
import com.study.base.BaseActivity;
import com.study.model.User;
import com.study.userful.util.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
public class ListViewActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    private List<String> mDatas;
    @BindView(R.id.tv_create_menu)
    TextView tvCreateMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_listview;
    }

    /**
     * 每次都会触发
     *
     * @param menu     就是我们的上下文菜单（可以通过 xml 也可以通过 代码实现）
     * @param v        要注册 menu 的 View
     * @param menuInfo 含有 menu 的信息，如果注册的 View  是 ListView 的话 menuInfo 则是 AdapterContextMenuInfo
     *                 否则为 null
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Log.e("onCreateContextMenu", "log");
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    /**
     * 选中后触发
     *
     * @param item 选中的 menu item
     * @return 没有用
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, item.getTitle() + "增加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    public void init() {


//        arrayAdapterUse();

//        simpleAdapterUse();

        baseAdapterUse();

        // 页面可以看到几个 item ，第一次就执行 getView多少次。当再出现一个 item 的时候再调用一次 getView
        // contentView 的个数为页面可见的个数 +1 。然后不可见的 item 放入回收栈中，等待下一次回收使用。

    }

    public void contextMenuUse() {

        registerForContextMenu(tvCreateMenu);
    }

    /**
     * 关于 choiceMode 属性的使用
     */
    public void choiceModeTest() {
        // none 、singleChoice 、multipleChoice
        // 填充数据
        String[] data = new String[]{"one", "two", "three", "four", "five"};
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
        lv.setItemsCanFocus(true);
        // getCheckedItemPosition -1 getCheckedItemPositions 管用
        // getCheckedItemCount() 获取选中的个数，多次点击取消
//        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, lv.getCheckedItemPosition() + "");
                Log.e(TAG, lv.getCheckedItemCount() + "");
            }
        });
    }


    /**
     * ArrayAdapter 的使用
     */
    public void arrayAdapterUse() {

        // 定义数据源
        List<User> adapterData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName("ArrayData" + i);
            adapterData.add(user);
        }

        // 创建 ArrayAdapter 适配器

        ArrayAdapter<User> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adapterData);
        lv.setAdapter(arrayAdapter);

    }

    /**
     * simpleAdapter 的使用
     */
    public void simpleAdapterUse() {
        String[] name = new String[]{"小明", "小华", "小李", "小王"};
        int[] age = new int[]{14, 18, 13, 10};
        String[] address = new String[]{"山东", "北京", "天津", "青岛"};
        int[] picture = new int[]{R.drawable.ic_launcher, R.drawable.icon_qubaogao, R.drawable.icon_qujiaofei,
                R.drawable.icon_ququyao};

        ArrayList<HashMap<String, Object>> listData = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", name[i]);
            hashMap.put("age", age[i]);
            hashMap.put("address", address[i]);
            hashMap.put("picture", picture[i]);
            listData.add(hashMap);
        }

        String[] form = new String[]{"name", "age", "address", "picture"};
        int[] to = new int[]{R.id.tv_one, R.id.tv_two, R.id.tv_three, R.id.tv_four};

        // 构造 SimpleAdapter 对象，设置适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listData, R.layout.item_simple_adapter, form, to);
        lv.setAdapter(simpleAdapter);
    }

    public void baseAdapterUse(){
        ArrayList<String> listData = new ArrayList<>();
        for (int i=0;i<30;i++){
            listData.add("BaseAdapter"+i);
        }
        MyAdapter myAdapter = new MyAdapter(this,listData);
        lv.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter {
        // 用来填充布局
        private LayoutInflater mInflater;
        private Context context;
        private List<String> listItem;

        // 构造方法
        MyAdapter(Context context, List<String> listItem) {
            this.context = context;
            this.listItem = listItem;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            L.e(TAG+Math.random(),"getCount()");
            return listItem.size();
        }

        @Override
        public Object getItem(int position) {
            L.e(TAG,"getItem()");
            return listItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            L.e(TAG,"getItemId()");
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            MyViewHolder myViewHolder;
////            if (convertView == null) {
////
////                convertView = mInflater.inflate(R.layout.item_rlv, parent, false);
////                myViewHolder = new MyViewHolder(convertView);
////                convertView.setTag(myViewHolder);
////            }
////            myViewHolder = (MyViewHolder) convertView.getTag();
////            String s = Math.random() +"ha";
////            myViewHolder.tv.setText(s);
           View convertView1 = mInflater.inflate(R.layout.item_rlv,parent,false);
           TextView textView =  convertView1.findViewById(R.id.tv);
            Log.e(TAG+Math.random(),"getView()");
           textView.setText("sww"+position);
            return convertView1;
        }
    }

    class MyViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.cv_item)
        CardView cvItem;

        MyViewHolder(View view) {
            ButterKnife.bind(this, view);

        }

    }

    class MyAdapter1 extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }


}
