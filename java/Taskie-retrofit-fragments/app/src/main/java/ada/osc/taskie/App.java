package ada.osc.taskie;

import android.app.Application;

import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.interaction.ApiInteractorImpl;
import ada.osc.taskie.networking.ApiService;
import ada.osc.taskie.networking.RetrofitUtil;
import retrofit2.Retrofit;

public class App extends Application {

    private static Retrofit retrofit;

    private static ApiService apiService;

    private static ApiInteractor apiInteractor;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = RetrofitUtil.createRetrofit();
        apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);
    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }
}
