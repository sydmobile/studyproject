package com.study.study_module.mvp.google.taskdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 说明：$
 * <p>
 * date: 2020/4/14 14:42
 *
 * @author syd
 * @version 1.0
 */
public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

    private TaskDetailContract.Presenter mPresenter;

    public static TaskDetailFragment newInstance(String taskId){
        Bundle arguments = new Bundle();
        arguments.putString("KEY",taskId);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setPresenter(TaskDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showEditTask(String taskId) {

    }

    @Override
    public void showCompletionStatus(boolean complete) {
        // 设置 UI
    }

    @Override
    public void showDescription(String description) {
        // 设置 UI
    }
}
