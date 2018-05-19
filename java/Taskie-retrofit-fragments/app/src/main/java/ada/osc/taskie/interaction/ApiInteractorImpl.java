package ada.osc.taskie.interaction;

import ada.osc.taskie.model.LoginResponse;
import ada.osc.taskie.model.RegistrationToken;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskList;
import ada.osc.taskie.networking.ApiService;
import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor {

    private final ApiService apiService;

    public ApiInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void registerUser(RegistrationToken registerRequest, Callback<RegistrationToken> callback) {
        apiService.registerUser(registerRequest).enqueue(callback);
    }

    @Override
    public void loginUser(RegistrationToken loginRequest, Callback<LoginResponse> callback) {
        apiService.loginUser(loginRequest).enqueue(callback);
    }

    @Override
    public void getTasks(Callback<TaskList> callback, String token) {
        apiService.getTasks(token).enqueue(callback);
    }

    @Override
    public void addNewTask(Task task, Callback<Task> callback, String token) {
        apiService.postNewTask(token, task).enqueue(callback);
    }
}
