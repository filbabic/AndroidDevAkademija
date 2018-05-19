package ada.osc.taskie.presentation;

import android.content.SharedPreferences;

import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.model.LoginResponse;
import ada.osc.taskie.model.RegistrationToken;
import ada.osc.taskie.ui.login.LoginContract;
import ada.osc.taskie.util.SharedPrefsUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private final ApiInteractor apiInteractor;
    private final SharedPreferences preferences;

    private LoginContract.View loginView;

    public LoginPresenter(ApiInteractor apiInteractor, SharedPreferences preferences) {
        this.apiInteractor = apiInteractor;
        this.preferences = preferences;
    }

    @Override
    public void setView(LoginContract.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void loginUser(RegistrationToken login) {
        if (login != null && !login.email.isEmpty() && !login.password.isEmpty()) {
            logUserIn(login);
        } else {
            loginView.showUserInvalidError();
        }
    }

    private void logUserIn(RegistrationToken login) {
        apiInteractor.loginUser(login, getLoginCallback());
    }

    private Callback<LoginResponse> getLoginCallback() {
        return new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    preferences.edit().putString(SharedPrefsUtil.TOKEN, response.body().mToken).apply();

                    loginView.onUserLoggedIn();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginView.showNetworkError();
            }
        };
    }
}
