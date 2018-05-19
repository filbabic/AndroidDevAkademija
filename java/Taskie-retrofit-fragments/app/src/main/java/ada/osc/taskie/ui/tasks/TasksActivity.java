package ada.osc.taskie.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.persistance.TaskRepository;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.ui.addTask.NewTaskActivity;
import ada.osc.taskie.ui.tasks.fragments.AllTasksFragment;
import ada.osc.taskie.ui.tasks.fragments.FavoriteTasksFragment;
import ada.osc.taskie.ui.tasks.fragments.TasksPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksActivity extends AppCompatActivity {

    private static final String TAG = TasksActivity.class.getSimpleName();
    private static final int REQUEST_NEW_TASK = 10;
    public static final String EXTRA_TASK = "task";

    TaskRepository mRepository = TaskRepository.getInstance();
    TaskAdapter mTaskAdapter;

    @BindView(R.id.fab_tasks_addNew)
    FloatingActionButton mNewTask;

    @BindView(R.id.fragmentContainer)
    ViewPager viewPager;

    private TasksPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        ButterKnife.bind(this);
        adapter = new TasksPagerAdapter(getSupportFragmentManager());

        List<Fragment> pages = new ArrayList<>();
        pages.add(new AllTasksFragment());
        pages.add(new FavoriteTasksFragment());

        adapter.setItems(pages);
        viewPager.setAdapter(adapter);
    }

    private void toastTask(Task task) {
        Toast.makeText(
                this,
                task.getTitle() + "\n" + task.getDescription(),
                Toast.LENGTH_SHORT
        ).show();
    }

    @OnClick(R.id.fab_tasks_addNew)
    public void startNewTaskActivity() {
        Intent newTask = new Intent();
        newTask.setClass(this, NewTaskActivity.class);
        startActivityForResult(newTask, REQUEST_NEW_TASK);
    }
}
