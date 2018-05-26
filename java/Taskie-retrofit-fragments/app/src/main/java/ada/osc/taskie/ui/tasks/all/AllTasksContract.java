package ada.osc.taskie.ui.tasks.all;

import java.util.List;

import ada.osc.taskie.model.Task;
import ada.osc.taskie.presentation.BasePresenter;

public interface AllTasksContract {

    interface View {

        void showTasks(List<Task> tasks);

        void showMoreTasks(List<Task> tasks);

        void onTaskRemoved(String taskId);

        void onTaskFavoriteStateChanged(String taskId);
    }

    interface Presenter extends BasePresenter<AllTasksContract.View> {

        void getTasks();

        void deleteTask(Task task);

        void setTaskFavorite(Task task);
    }
}
