package ada.osc.taskie.ui.addTask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import ada.osc.taskie.networking.ApiService;
import ada.osc.taskie.networking.RetrofitUtil;
import ada.osc.taskie.util.SharedPrefsUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewTaskActivity extends AppCompatActivity {

	@BindView(R.id.edittext_newtask_title)	EditText mTitleEntry;
	@BindView(R.id.edittext_newtask_description) EditText mDescriptionEntry;
	@BindView(R.id.spinner_newtask_priority) Spinner mPriorityEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
		ButterKnife.bind(this);
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

		Task newTask = new Task(title, description, priority);
		createNewNote(newTask);

		finish();
	}

	private void createNewNote(Task taskToSave) {
		Retrofit retrofit = RetrofitUtil.createRetrofit();
		ApiService apiService = retrofit.create(ApiService.class);

		Call postNewTaskCall = apiService
				.postNewTask(SharedPrefsUtil.getPreferencesField(getApplicationContext()
						, SharedPrefsUtil.TOKEN), taskToSave);

		postNewTaskCall.enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response) {
				if (response.isSuccessful()) {
					finish();
				}
			}

			@Override
			public void onFailure(Call call, Throwable t) {

			}
		});

	}
}
