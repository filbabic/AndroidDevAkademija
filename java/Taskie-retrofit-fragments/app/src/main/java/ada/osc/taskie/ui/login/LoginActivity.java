package ada.osc.taskie.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import ada.osc.taskie.R;
import ada.osc.taskie.model.LoginResponse;
import ada.osc.taskie.model.RegistrationToken;
import ada.osc.taskie.networking.ApiService;
import ada.osc.taskie.networking.RetrofitUtil;
import ada.osc.taskie.util.SharedPrefsUtil;
import ada.osc.taskie.ui.tasks.TasksActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.user_email)
    EditText mUserEmail;
    @BindView(R.id.user_password)
    EditText mUserPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login)
    void onLoginButtonClick(){
        loginUser();
    }

    @OnClick(R.id.button_register)
    void onRegisterButtonClick() {

    }

    private void loginUser() {

    }

    private void startNotesActivity() {
        Intent intent = new Intent();
        intent.setClass(this, TasksActivity.class);
        startActivity(intent);
    }
}
