package ada.osc.taskie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import ada.osc.taskie.R;
import ada.osc.taskie.TaskRepository;
import ada.osc.taskie.model.Task;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class TasksActivity extends AppCompatActivity {

	private static final String TAG = TasksActivity.class.getSimpleName();
	private static final int REQUEST_NEW_TASK = 10;
	public static final String EXTRA_TASK = "task";

	TaskRepository mRepository = TaskRepository.getInstance();
	TaskAdapter mTaskAdapter;
	private Realm mRealm;

    public static final int SHARED_PREFERENCES_TAG = 1;

    @BindView(R.id.fab_tasks_addNew) FloatingActionButton mNewTask;
	@BindView(R.id.recycler_tasks) RecyclerView mTasksRecycler;

	TaskClickListener mListener = new TaskClickListener() {
		@Override
		public void onClick(Task task) {
			toastTask(task);
		}

		@Override
		public void onLongClick(Task task) {
			mRepository.removeTask(task);
			updateTasksDisplay();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks);

		mRealm = Realm.getDefaultInstance();

		ButterKnife.bind(this);
		setUpRecyclerView();
		updateTasksDisplay();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateTasksDisplay();
	}

	private void setUpRecyclerView() {

		int orientation = LinearLayoutManager.VERTICAL;

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
				this,
				orientation,
				false
		);

		RecyclerView.ItemDecoration decoration =
				new DividerItemDecoration(this, orientation);

		RecyclerView.ItemAnimator animator = new DefaultItemAnimator();

		mTaskAdapter = new TaskAdapter(mListener);

		mTasksRecycler.setLayoutManager(layoutManager);
		mTasksRecycler.addItemDecoration(decoration);
		mTasksRecycler.setItemAnimator(animator);
		mTasksRecycler.setAdapter(mTaskAdapter);
	}

	private void updateTasksDisplay() {
//		List<Task> tasks = mRepository.getTasks();

		RealmResults<Task> tasks = mRealm.where(Task.class).findAll();

		mTaskAdapter.updateTasks(tasks);
		for (Task t : tasks){
			Log.d(TAG, t.getTitle());
		}
	}

	private void toastTask(Task task) {
		Toast.makeText(
				this,
				task.getTitle() + "\n" + task.getDescription(),
				Toast.LENGTH_SHORT
		).show();
	}

	@OnClick(R.id.fab_tasks_addNew)
	public void startNewTaskActivity(){
		Intent newTask = new Intent();
		newTask.setClass(this, NewTaskActivity.class);
		startActivityForResult(newTask, REQUEST_NEW_TASK);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == REQUEST_NEW_TASK && resultCode == RESULT_OK) {
			if (data != null && data.hasExtra(EXTRA_TASK)) {
				Task task = (Task) data.getSerializableExtra(EXTRA_TASK);
				mRepository.saveTask(task);
				updateTasksDisplay();
			}
		}
	}
}
