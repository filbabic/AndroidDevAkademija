package ada.osc.taskie.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.ui.addTask.NewTaskActivity;
import ada.osc.taskie.ui.tasks.adapter.TasksPagerAdapter;
import ada.osc.taskie.ui.tasks.all.AllTasksFragment;
import ada.osc.taskie.ui.tasks.favorite.FavoriteTasksFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksActivity extends AppCompatActivity {

    @BindView(R.id.fab_tasks_addNew)
    FloatingActionButton mNewTask;

    @BindView(R.id.fragmentContainer)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        ButterKnife.bind(this);
        TasksPagerAdapter adapter = new TasksPagerAdapter(getSupportFragmentManager());

        List<Fragment> pages = new ArrayList<>();
        pages.add(new AllTasksFragment());
        pages.add(new FavoriteTasksFragment());

        adapter.setItems(pages);
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.fab_tasks_addNew)
    public void startNewTaskActivity() {
        startActivity(new Intent(this, NewTaskActivity.class));
    }
}
