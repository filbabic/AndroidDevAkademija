package ada.osc.taskie.ui.login;

import ada.osc.taskie.model.RegistrationToken;

public interface LoginContract {

    interface View {

        void onUserLoggedIn();

        void showNetworkError();

        void showUserInvalidError();
    }

    interface Presenter {

        void setView(LoginContract.View loginView);

        void loginUser(RegistrationToken login);
    }
}
