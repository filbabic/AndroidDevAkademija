package ada.osc.taskie.ui.addTask;

import ada.osc.taskie.model.Task;

public interface NewTaskContract {

    interface View {

        void onTaskCreated();

        void showNetworkError();

        void showTaskError();
    }

    interface Presenter {

        void setView(View newTaskView);

        void createTask(Task task);

    }
}
