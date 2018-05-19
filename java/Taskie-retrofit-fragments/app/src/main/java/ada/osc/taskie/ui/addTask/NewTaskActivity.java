package ada.osc.taskie.ui.addTask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ada.osc.taskie.App;
import ada.osc.taskie.R;
import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import ada.osc.taskie.networking.ApiService;
import ada.osc.taskie.networking.RetrofitUtil;
import ada.osc.taskie.presentation.NewTaskPresenter;
import ada.osc.taskie.util.SharedPrefsUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewTaskActivity extends AppCompatActivity implements NewTaskContract.View {

    @BindView(R.id.edittext_newtask_title)
    EditText mTitleEntry;
    @BindView(R.id.edittext_newtask_description)
    EditText mDescriptionEntry;
    @BindView(R.id.spinner_newtask_priority)
    Spinner mPriorityEntry;

    private NewTaskContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);

        presenter = new NewTaskPresenter(App.getPreferences(), App.getApiInteractor());
        presenter.setView(this);
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
    public void saveTask() {
        String title = mTitleEntry.getText().toString();
        String description = mDescriptionEntry.getText().toString();
        TaskPriority priority = (TaskPriority) mPriorityEntry.getSelectedItem();

        Task newTask = new Task(title, description, priority);

        presenter.createTask(newTask);
    }

    @Override
    public void onTaskCreated() {
        finish();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, "Network error, please try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTaskError() {
        Toast.makeText(this, "Task is invalid", Toast.LENGTH_SHORT).show();
    }
}
