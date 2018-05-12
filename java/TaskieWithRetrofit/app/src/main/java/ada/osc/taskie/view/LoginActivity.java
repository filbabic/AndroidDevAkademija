package ada.osc.taskie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import ada.osc.taskie.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    }

    @OnClick(R.id.button_register)
    void onRegisterButtonClick() {

    }
}
