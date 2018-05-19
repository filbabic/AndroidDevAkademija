package ada.osc.myfirstweatherapp;

import android.app.Application;
import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 01/04/2016.
 */
public class App extends Application {

    private static App sInstance;
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        retrofit = provideRestClient();
    }

    public static App getInstance() {
        return sInstance;
    }

    @NonNull
    private Retrofit provideRestClient() {
        return new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
