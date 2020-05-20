package com.study.study_module.mvp.google.taskdetail;

/**
 * 说明：$
 * <p>
 * date: 2020/4/14 14:37
 *
 * @author syd
 * @version 1.0
 */
public class TaskDetailPresenter implements TaskDetailContract.Presenter {
    private final TaskDetailContract.View mTaskDetailView;

    public TaskDetailPresenter(TaskDetailContract.View mTaskDetailView) {
        this.mTaskDetailView = mTaskDetailView;
        this.mTaskDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        // 执行任务
    }



    @Override
    public void editTask() {
        mTaskDetailView.showDescription("description");

    }

    @Override
    public void completeTask() {
        mTaskDetailView.showCompletionStatus(true);
    }
}
