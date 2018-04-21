package ada.osc.passwordstealer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordActivity extends AppCompatActivity {

    private static final String TAG = PasswordActivity.class.getSimpleName();

    @BindView(R.id.textview_password_passwordDisplay) TextView mPasswordDisplay;
    @BindView(R.id.edittext_password_passwordEntry) EditText mPasswordEntry;
    @BindView(R.id.button_password_passwordSave) Button mPasswordSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_password_passwordSave)
    public void savePassword(){

        String text = mPasswordEntry.getText().toString();
        mPasswordDisplay.setText(text);
        mPasswordEntry.setText(null);

        toastMessage(text);
        logMessage(text);
    }

    private void logMessage(String message) {

        Log.d(TAG, message);
        Log.w(TAG, message);
        Log.e(TAG, message);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
