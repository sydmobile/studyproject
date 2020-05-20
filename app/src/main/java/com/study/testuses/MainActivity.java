package com.study.testuses;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.study.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

	RefreshableView refreshableView;
	ListView listView;
	ArrayAdapter<String> adapter;
	String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main1);
		refreshableView = findViewById(R.id.refreshable_view);
		listView = findViewById(R.id.list_view);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		listView.setAdapter(adapter);
		refreshableView.setOnRefreshListener(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshableView.finishRefreshing();
		}, 0);



	}

	public static void main(String[] args) {
//		String[] category = new String[] {"学校","医院","大型场馆","商业综合体","工厂企业","交通枢纽","静态交通","商务住宅"
//				,"场馆","公司"};
		// 空间环境
		String[] category = new String[] {"展厅","展墙","展位","特色空间","活动庆典","户外围挡","SIS系统"};
		// 装置·艺术
//		String[] category = new String[] {"雕塑","摆件","互动装置","商业美陈"};
//		String[] category = new String[] {"视野理念","视野定位","视野客户"};
//		String[] image = new String[]{
//				"https://m.360buyimg.com/babel/jfs/t1/90075/19/16500/186203/5e7c892fEc87cd762/fb73c6c81b3248a6.jpg!q70.jpg.webp",
//				"https://m.360buyimg.com/babel/jfs/t1/86468/36/16387/184066/5e7aae16E721ea13c/e78cc12cb6fea41d.jpg!q70.jpg.webp",
//				"https://m.360buyimg.com/babel/jfs/t1/108920/25/10132/130791/5e7ad0ccE037c0337/a221879680c901d8.jpg!q70.jpg.webp",
//				"https://m.360buyimg.com/babel/jfs/t1/94575/12/16354/176983/5e79b95bE589562a3/61f940420c289191.jpg!q70.jpg.webp",
//				"https://m.360buyimg.com/babel/jfs/t1/98335/11/16569/66414/5e7c84efE8fb862b9/0eed36d63af6e55a.jpg!q70.jpg.webp",
//				"https://m.360buyimg.com/babel/jfs/t1/90420/3/16509/121915/5e7c8193E6d6e6d98/4086a378b7ea07de.jpg!q70.jpg.webp",
//				"https://m.360buyimg.com/babel/jfs/t1/106135/34/16406/188122/5e7c6d07Efa76fc51/0b9d7f0be5b7c024.png"
//		};

		String[] image = new String[]{
				"/assets/images/1gai.jpg",
				"/assets/images/2gai.jpg",
				"/assets/images/3gai.jpg",
				"/assets/images/4.jpg",
				"/assets/images/6.jpg",
				"/assets/images/bei1.jpg",
				"/assets/images/bei2.jpg",
				"/assets/images/bei3.jpg",
				"/assets/images/5.jpg",
		};
		String message = "北京外国语大学在北京市海淀区三环路两侧分设东、西两个校区。视野文化参与规划设计了东校区的标识系统。";
		String[] title = new String[]{"北京大学","海淀医院","国家会议中心","万达广场","雄安新区项目","大兴机场","京九高速","万科天下","北京科技馆","北京视野文化有限公司"};
		List<Group> list = new ArrayList<>();
		for (int i=1;i<category.length+1;i++){
			Group group = new Group();
			group.setId("id"+i);
			group.setName(category[i-1]);
			List list1 = new ArrayList();
			int random = (4+(int)(Math.random()*(2)));
			for (int j =random;j<8;j++){
				List<String> list2 = new ArrayList<>();
				for (int k=random;k<7;k++){
					int x = (int) (Math.random()*9);
					list2.add(image[x]);
				}
				Item item = new Item(group.getId()+j,category[i-1]+"("+j+")",list2
				,message);
				list1.add(item);
			}
			group.setCategory(list1);
			list.add(group);

		}
		String json = new Gson().toJson(list);
		System.out.println(json);


	}

}
