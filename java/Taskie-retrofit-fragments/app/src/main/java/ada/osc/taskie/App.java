package ada.osc.taskie;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import ada.osc.taskie.interaction.ApiInteractor;
import ada.osc.taskie.interaction.ApiInteractorImpl;
import ada.osc.taskie.networking.ApiService;
import ada.osc.taskie.networking.RetrofitUtil;
import retrofit2.Retrofit;

public class App extends Application {

    private static ApiInteractor apiInteractor;

    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);

        preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }
}
