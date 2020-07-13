package com.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.base.BaseActivity;
import com.study.model.Command;
import com.study.study_module.eventbus.FistEventBusActivity;
import com.study.study_module.fragment.FragmentMainActivity;
import com.study.study_module.lambda.LambdaActivity;
import com.study.study_module.material.MaterialActivity;
import com.study.study_module.material.MaterialCompatActivity;
import com.study.study_module.material.MaterialDesignActivity;
import com.study.study_module.mvp_test.UserLoginActivity;
import com.study.study_module.observer.DesignModeActivity;
import com.study.study_module.okhttp.OkHttpBaseUseActivity;
import com.study.study_module.recyclerview.PulmListViewActivity;
import com.study.study_module.recyclerview.RecyclerViewActivityOne;
import com.study.study_module.retrofit.Retrofit2BaseUseActivity;
import com.study.study_module.rx.RxJavaTest;
import com.study.study_module.sensor.SensorActivity;
import com.study.study_module.textview.TextViewActivity;
import com.study.study_module.video.VideoTest;
import com.study.study_module.viewstub.ViewStubActivity;
import com.study.testuses.TestActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    List<Command> listCommand = new ArrayList<>();
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setSupportActionBar(findViewById(R.id.tb));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    public void initView() {
        addCommand(Command.getInstance("ViewStub", ViewStubActivity.class));
        addCommand(Command.getInstance("rlv", RecyclerViewActivityOne.class));
        addCommand(Command.getInstance("自定义加载ListView", PulmListViewActivity.class));
        addCommand(Command.getInstance("Test", TestActivity.class));
        addCommand(Command.getInstance("Sensor", SensorActivity.class));
        addCommand(Command.getInstance("Lambda", LambdaActivity.class));
        addCommand(Command.getInstance("MVP_demo", UserLoginActivity.class));
        addCommand(Command.getInstance("设计模式", DesignModeActivity.class));
        addCommand(Command.getInstance("EventBus", FistEventBusActivity.class));
        addCommand(Command.getInstance("TextView高级", TextViewActivity.class));
        addCommand(Command.getInstance("Material", MaterialDesignActivity.class));
        addCommand(Command.getInstance("Video", VideoTest.class));
        addCommand(Command.getInstance("Retrofit使用", Retrofit2BaseUseActivity.class));
        addCommand(Command.getInstance("RxJava", RxJavaTest.class));
        addCommand(Command.getInstance("okhttp", OkHttpBaseUseActivity.class));
        addCommand(Command.getInstance("Material控件", MaterialCompatActivity.class));
        addCommand(Command.getInstance("Fragment", FragmentMainActivity.class));
        rlv.setLayoutManager(new GridLayoutManager(this, 4));
        rlv.setAdapter(new Adapter(this));
        tb.inflateMenu(R.menu.menu);
    }

    public void addCommand(Command command) {
        listCommand.add(command);
    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
//
//        return true;
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_okhttp:
//                Intent intent8 = new Intent("myAction");
//                intent8.addCategory(Intent.CATEGORY_DEFAULT);
//                startActivity(intent8);
//                break;
//            case R.id.tv_my_refresh:
//                Intent intent9 = new Intent("refresh");
//                startActivity(intent9);
//                break;
//
//            case R.id.tv_rxjava:
//                Intent intent15 = new Intent(this, RxActivity.class);
//                startActivity(intent15);
//                break;
//            case R.id.tv_urlscheme:
//                Uri data = Uri.parse("urlscheme://auth_activity");
//                Intent intent17 = new Intent(Intent.ACTION_VIEW, data);
//                intent17.setData(data);
//                startActivity(intent17);
//                break;

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bt;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            bt = itemView.findViewById(R.id.bt);
        }
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {
        LayoutInflater inflater;

        Context context;

        Adapter(Context context) {
            inflater = LayoutInflater.from(context);
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_button, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.bt.setOnClickListener(v -> {
                switch (listCommand.get(viewHolder.getAdapterPosition()).getCategory()) {
                    case 0:
                        Intent intent = new Intent(context,
                                listCommand.get(viewHolder.getAdapterPosition()).getCls());
                        startActivity(intent);
                        break;
                    case 1:

                }

            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bt.setText(listCommand.get(position).getDesc());
        }

        @Override
        public int getItemCount() {
            return listCommand == null ? 0 : listCommand.size();
        }
    }

}
