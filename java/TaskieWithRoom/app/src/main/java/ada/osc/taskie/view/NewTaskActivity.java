package ada.osc.taskie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import ada.osc.taskie.R;
import ada.osc.taskie.database.TaskDao;
import ada.osc.taskie.database.TaskRoomDatabase;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTaskActivity extends AppCompatActivity {

	@BindView(R.id.edittext_newtask_title)	EditText mTitleEntry;
	@BindView(R.id.edittext_newtask_description) EditText mDescriptionEntry;
	@BindView(R.id.spinner_newtask_priority) Spinner mPriorityEntry;

	private TaskDao mTaskDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
		ButterKnife.bind(this);
		initDao();
		setUpSpinnerSource();
	}

	private void setUpSpinnerSource() {
		mPriorityEntry.setAdapter(
				new ArrayAdapter<TaskPriority>(
						this, android.R.layout.simple_list_item_1, TaskPriority.values()
				)
		);
		mPriorityEntry.setSelection(0);
	}

	private void initDao() {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(this);
        mTaskDao = database.taskDao();
    }

	@OnClick(R.id.imagebutton_newtask_savetask)
	public void saveTask(){
		String title = mTitleEntry.getText().toString();
		String description = mDescriptionEntry.getText().toString();
		TaskPriority priority = (TaskPriority) mPriorityEntry.getSelectedItem();

		Task newTask = new Task(title, description, priority);

		mTaskDao.insert(newTask);

//		Intent saveTaskIntent = new Intent(this, TasksActivity.class);
//		saveTaskIntent.putExtra(TasksActivity.EXTRA_TASK, newTask);
//		setResult(RESULT_OK, saveTaskIntent);
		finish();
	}
}
