package ada.osc.taskie.presentation;

import android.content.SharedPreferences;

import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.ui.addTask.NewTaskContract;
import ada.osc.taskie.util.SharedPrefsUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTaskPresenter implements NewTaskContract.Presenter {

    private final SharedPreferences preferences;
    private final ApiInteractor apiInteractor;

    private NewTaskContract.View newTaskView;

    public NewTaskPresenter(SharedPreferences preferences, ApiInteractor apiInteractor) {
        this.preferences = preferences;
        this.apiInteractor = apiInteractor;
    }

    @Override
    public void setView(NewTaskContract.View newTaskView) {
        this.newTaskView = newTaskView;
    }

    @Override
    public void createTask(Task task) {
        if (task != null && !task.getDescription().isEmpty()
                && !task.getTitle().isEmpty()) {
            apiInteractor.addNewTask(task, getNewTaskCallback(), preferences.getString(SharedPrefsUtil.TOKEN, ""));
        } else {
            newTaskView.showTaskError();
        }
    }

    private Callback<Task> getNewTaskCallback() {
        return new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newTaskView.onTaskCreated();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                newTaskView.showNetworkError();
            }
        };
    }
}
