package ada.osc.taskie.ui.tasks.all;

import java.util.List;

import ada.osc.taskie.model.Task;

public interface AllTasksContract {

    interface View {

        void showTasks(List<Task> tasks);

        void showMoreTasks(List<Task> tasks);

        void onTaskRemoved(String taskId);

        void onTaskFavoriteStateChanged(String taskId);
    }

    interface Presenter {

        void setView(View allTasksView);

        void getTasks();

        void deleteTask(Task task);

        void setTaskFavorite(Task task);
    }
}
