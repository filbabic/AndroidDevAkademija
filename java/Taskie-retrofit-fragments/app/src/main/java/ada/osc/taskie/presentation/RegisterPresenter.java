package ada.osc.taskie.presentation;

import ada.osc.taskie.model.RegistrationToken;
import ada.osc.taskie.ui.register.RegisterView;

public interface RegisterPresenter {

    void setView(RegisterView registerView);

    void registerUser(RegistrationToken token);
}
