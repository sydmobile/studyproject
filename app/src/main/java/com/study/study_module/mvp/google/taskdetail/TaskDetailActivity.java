package com.study.study_module.mvp.google.taskdetail;

import android.os.Bundle;

import com.study.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 说明：$
 * <p>
 * date: 2020/4/14 14:49
 *
 * @author syd
 * @version 1.0
 */
public class TaskDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TaskDetailFragment fragment = TaskDetailFragment.newInstance("xxxx");
        new TaskDetailPresenter(fragment);
    }
}
