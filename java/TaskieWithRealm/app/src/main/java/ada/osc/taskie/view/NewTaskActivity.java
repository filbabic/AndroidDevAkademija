package ada.osc.taskie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class NewTaskActivity extends AppCompatActivity {

	@BindView(R.id.edittext_newtask_title)	EditText mTitleEntry;
	@BindView(R.id.edittext_newtask_description) EditText mDescriptionEntry;
	@BindView(R.id.spinner_newtask_priority) Spinner mPriorityEntry;

	private Realm mRealm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
		ButterKnife.bind(this);
		mRealm = Realm.getDefaultInstance();
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

	@OnClick(R.id.imagebutton_newtask_savetask)
	public void saveTask(){
		String title = mTitleEntry.getText().toString();
		String description = mDescriptionEntry.getText().toString();
		TaskPriority priority = (TaskPriority) mPriorityEntry.getSelectedItem();

//		Task newTask = new Task(title, description, priority);

		mRealm.beginTransaction();
		Task newTask = mRealm.createObject(Task.class, UUID.randomUUID().toString());
		newTask.setTitle(title);
		newTask.setDescription(description);
		newTask.setTaskPriorityEnum(priority);
		mRealm.commitTransaction();

//		mRealm.executeTransaction(new Realm.Transaction() {
//			@Override
//			public void execute(Realm realm) {
//				mRealm.createObject(Task.class, UUID.randomUUID().toString());
//			}
//		});

//		Intent saveTaskIntent = new Intent(this, TasksActivity.class);
//		saveTaskIntent.putExtra(TasksActivity.EXTRA_TASK, newTask);
//		setResult(RESULT_OK, saveTaskIntent);
		finish();
	}
}
