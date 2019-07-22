package com.syd.study.textview;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：TextView  测试
 * <p>
 * date: 2019-07-18 15:17
 *
 * @author syd
 * @version 1.0
 */
public class TextViewActivity extends BaseActivity {

    @BindView(R.id.tv_one)
    TextView mTvOne;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.tv_three)
    TextView mTvThree;
    @BindView(R.id.tv_four)
    TextView mTvFour;
    @BindView(R.id.tv_five)
    TextView mTvFive;
    private static final String TAG = "TextViewActivity";
    Dialog dialog;
    DialogMy dialogMy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_main);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        mTvOne.setText(Html.fromHtml("北京发布雾霾黄色预警，<font color='#ff0000'><big><big>外出携带好</big></big></font>口罩"));
        mTvTwo.setText(Html.fromHtml("北京发布雾霾黄色预警，<h3><font color = '#FF0000'> 外出携带好</font></h3>口罩"));
        mTvThree.setText("北京市发布雾霾黄色预警，外出携带好口罩");
        Spannable span = new SpannableString(mTvThree.getText());
        span.setSpan(new AbsoluteSizeSpan(48),11,16,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.YELLOW),11,16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new BackgroundColorSpan(Color.RED),11,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvThree.setText(span);
        initDialog();
        mTvFive.setOnClickListener(v -> {
            dialogMy.showDialog();

        });

    }

    public void initDialog(){
//        dialog = new Dialog(this,R.style.MyDialog_address);
//        dialog.setContentView(R.layout.dialog_remind_text);
        dialogMy = new DialogMy(this,R.style.MyDialog_address,new DialogMy.CallBack() {
            @Override
            public void init(DialogMy dialogMy) {
                TextView textView = dialogMy.findViewById(R.id.tv_title);
                textView.setText("有意思");
                Window window = dialogMy.getWindow();
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(layoutParams);
            }
        },R.layout.dialog_remind_text);
    }
}
