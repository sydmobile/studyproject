package com.study.study_module.material;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.study.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：Material Design
 * <p>
 * date: 2019/11/7 9:22
 *
 * @author syd
 * @version 1.0
 */
public class MaterialActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_jian)
    Button btJian;
    @BindView(R.id.bt_snackbar)
    Button btSnackbar;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.seekBar2)
    SeekBar seekBar2;
    @BindView(R.id.ll)
    LinearLayout ll;
//    @BindView(R.id.rg)
//    RadioGroup rg;
//    @BindView(R.id.calendarView)
//    CalendarView calendarView;
//    @BindView(R.id.seekBar)
//    SeekBar seekBar;
//    @BindView(R.id.seekBar2)
//    SeekBar seekBar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        ButterKnife.bind(this);
        initView();
    }


    class MyViewOutlineProvider extends ViewOutlineProvider {

        @Override
        public void getOutline(View view, Outline outline) {
            Log.e("v", view.toString());
            outline.setRect(0, 0, view.getWidth() + 20, view.getHeight() + 20);
        }
    }


    public void initView() {

//        tvNext.setOnClickListener(this);
////        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10, getResources()
// .getDisplayMetrics());
////
////        iv.setElevation(px);
//        iv.setTranslationZ(10);
//        iv.setOutlineProvider(new MyViewOutlineProvider());
//        iv.setElevation(90);
//        iv.setTranslationZ(60);
//        iv.setClipToOutline(true);
        bt.setOutlineProvider(new MyViewOutlineProvider());
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.setElevation(bt.getElevation() + 10);
            }
        });

        btJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.setElevation(bt.getElevation() - 10);
            }
        });
        btSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(ll,"snackbar",Snackbar.LENGTH_SHORT).setAction("action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setActionTextColor(Color.RED).show();
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                Intent intent = new Intent(this, MaterialCompatActivity.class);
                startActivity(intent);
                break;
        }
    }
}
