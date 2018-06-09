package ada.osc.taskie.ui.tasks.all;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ada.osc.taskie.App;
import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.presentation.AllTasksPresenter;
import ada.osc.taskie.listener.TaskClickListener;
import ada.osc.taskie.ui.tasks.adapter.TaskAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllTasksFragment extends Fragment implements AllTasksContract.View, TaskClickListener {

    @BindView(R.id.tasks)
    RecyclerView tasks;

    private TaskAdapter taskAdapter;

    private AllTasksContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        presenter = new AllTasksPresenter(App.getApiInteractor(), App.getPreferences());
        presenter.setView(this);

//        taskAdapter = new TaskAdapter(this);

        tasks.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasks.setItemAnimator(new DefaultItemAnimator());
        tasks.setAdapter(taskAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getTasks();
    }

    private void updateTasksDisplay(List<Task> taskList) {
//        taskAdapter.updateTasks(taskList);
        for (Task t : taskList) {
            Log.d("taskovi", t.getTitle());
        }
    }

    @Override
    public void showTasks(List<Task> tasks) {
//        taskAdapter.updateTasks(tasks);
    }

    @Override
    public void showMoreTasks(List<Task> tasks) {
        // TODO: 19/05/2018 add lazy loading/pagination
    }

    @Override
    public void onTaskRemoved(String taskId) {
//        taskAdapter.removeTask(taskId);
    }

    @Override
    public void onTaskFavoriteStateChanged(String taskId) {
        // TODO: 19/05/2018 add logic for this
    }

    @Override
    public void onClick(Task task) {

    }

    @Override
    public void onLongClick(Task task) {

    }
}
