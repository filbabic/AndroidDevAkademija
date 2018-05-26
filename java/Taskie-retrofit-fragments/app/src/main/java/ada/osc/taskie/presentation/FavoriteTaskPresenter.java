package ada.osc.taskie.presentation;

import android.content.SharedPreferences;

import java.util.List;

import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskList;
import ada.osc.taskie.ui.tasks.favorite.FavoriteTasksContract;
import ada.osc.taskie.util.SharedPrefsUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteTaskPresenter implements FavoriteTasksContract.Presenter {

    private final SharedPreferences preferences;
    private final ApiInteractor apiInteractor;

    private FavoriteTasksContract.View favoriteTasksView;

    public FavoriteTaskPresenter(SharedPreferences preferences, ApiInteractor apiInteractor) {
        this.preferences = preferences;
        this.apiInteractor = apiInteractor;
    }

    @Override
    public void setView(FavoriteTasksContract.View favoriteTasksView) {
        this.favoriteTasksView = favoriteTasksView;
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void getTasks() {
        apiInteractor.getFavoriteTasks(getFavoriteTasksCallback(), preferences.getString(SharedPrefsUtil.TOKEN, ""));
    }

    private Callback<TaskList> getFavoriteTasksCallback() {
        return new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Task> tasks = response.body().mTaskList;

                    favoriteTasksView.showTasks(tasks);
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
