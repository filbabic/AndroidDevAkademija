package ada.osc.taskie.interaction;

import ada.osc.taskie.model.LoginResponse;
import ada.osc.taskie.model.RegistrationToken;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskList;
import retrofit2.Callback;

public interface ApiInteractor {

    void registerUser(RegistrationToken registerRequest, Callback<RegistrationToken> callback);

    void loginUser(RegistrationToken loginRequest, Callback<LoginResponse> callback);

    void getTasks(Callback<TaskList> callback, String token);

    void addNewTask(Task task, Callback<Task> callback, String token);
}
