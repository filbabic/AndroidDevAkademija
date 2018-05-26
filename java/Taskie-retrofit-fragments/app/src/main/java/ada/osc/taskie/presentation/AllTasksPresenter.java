package ada.osc.taskie.presentation;

import android.content.SharedPreferences;

import java.util.List;

import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskList;
import ada.osc.taskie.ui.tasks.all.AllTasksContract;
import ada.osc.taskie.util.SharedPrefsUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTasksPresenter implements AllTasksContract.Presenter {

    private final ApiInteractor apiInteractor;
    private final SharedPreferences preferences;

    private AllTasksContract.View allTasksView;

    public AllTasksPresenter(ApiInteractor apiInteractor, SharedPreferences preferences) {
        this.apiInteractor = apiInteractor;
        this.preferences = preferences;
    }

    @Override
    public void setView(AllTasksContract.View allTasksView) {
        this.allTasksView = allTasksView;
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void getTasks() {
        apiInteractor.getTasks(getAllTasksCallback(), preferences.getString(SharedPrefsUtil.TOKEN, ""));
    }

    private Callback<TaskList> getAllTasksCallback() {
        return new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Task> tasks = response.body().mTaskList;

                    allTasksView.showTasks(tasks);
                }
            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                // TODO: 19/05/2018 add error handling
            }
        };
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void setTaskFavorite(Task task) {

    }
}
