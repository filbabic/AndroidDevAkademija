package ada.osc.taskie.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import ada.osc.taskie.App;
import ada.osc.taskie.R;
import ada.osc.taskie.model.RegistrationToken;
import ada.osc.taskie.presentation.RegisterPresenter;
import ada.osc.taskie.presentation.RegisterPresenterImpl;
import ada.osc.taskie.ui.login.LoginActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.user_email)
    EditText mUserEmail;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.user_password)
    EditText mUserPwd;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_register);
        presenter = new RegisterPresenterImpl(App.getApiInteractor());
        presenter.setView(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login)
    void onLoginButtonClick() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.button_register)
    void onRegisterButtonClick() {
        registerUser();
    }

    private void registerUser() {
        RegistrationToken user = new RegistrationToken(mUsername.getText().toString(),
                mUserEmail.getText().toString(),
                mUserPwd.getText().toString());

        presenter.registerUser(user);
    }

    @Override
    public void showUserInvalidError() {
        Toast.makeText(this, "User is not valid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserRegistered() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, "Network error, please try again", Toast.LENGTH_SHORT).show();
    }
}
