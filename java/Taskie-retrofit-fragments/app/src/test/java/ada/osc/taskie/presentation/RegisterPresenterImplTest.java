package ada.osc.taskie.presentation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.model.RegistrationToken;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import ada.osc.taskie.ui.register.RegisterView;
import retrofit2.Callback;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterPresenterImplTest {

    private RegisterPresenterImpl presenter;

    @Mock
    ApiInteractor interactor;

    @Mock
    RegisterView view;

    @Before
    public void setUp() throws Exception {
        presenter = new RegisterPresenterImpl(interactor);
        presenter.setView(view);
    }

    @Test
    public void createNewUserCallsApiInteractorRegisterUser() {
        presenter.createNewUser(new RegistrationToken("", "", ""));

        verify(interactor)
                .registerUser(any(RegistrationToken.class), any(Callback.class));

        verifyNoMoreInteractions(interactor);
    }

    @Test
    public void registerUserShowsErrorDataIsNull() {
        presenter.registerUser(null);

        verifyZeroInteractions(interactor);
        verify(view).showUserInvalidError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void registerUserShowsErrorEmailIsEmpty() {
        presenter.registerUser(new RegistrationToken("dawd", "", "dawd"));

        verifyZeroInteractions(interactor);
        verify(view).showUserInvalidError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void registerUserShowsErrorUsernameIsEmpty() {
        presenter.registerUser(new RegistrationToken("", "fdawd", "eacawd"));

        verifyZeroInteractions(interactor);
        verify(view).showUserInvalidError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void registerUserShowsErrorPasswordIsEmpty() {
        presenter.registerUser(new RegistrationToken("dadwa", "dawdwad", ""));

        verifyZeroInteractions(interactor);
        verify(view).showUserInvalidError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void registerUserCreatesANewUser() {
        presenter.registerUser(new RegistrationToken("username", "email", "password"));

        verify(interactor).registerUser(any(RegistrationToken.class), any(Callback.class));
        verifyNoMoreInteractions(view);
    }
}