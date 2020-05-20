package com.study.study_module.mvp.google.taskdetail;

import com.study.study_module.mvp.google.BasePresenter;
import com.study.study_module.mvp.google.BaseView;

/**
 * 说明：TaskDetailContract
 * <p>
 * date: 2020/4/14 14:27
 *
 * @author syd
 * @version 1.0
 */
public interface TaskDetailContract {
   interface Presenter extends BasePresenter{
        void editTask();
        void completeTask();
   }
   interface View extends BaseView<Presenter> {
        void showEditTask(String taskId);
        void showCompletionStatus(boolean complete);
        void showDescription(String description);
   }
}
