package ada.osc.taskie.networking;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import ada.osc.taskie.App;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ada.osc.taskie.util.SharedPrefsUtil.TOKEN;

public class RetrofitUtil {

    public static final String BASE_URL = "https://authenticationexample.herokuapp.com/";

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(okHttpClient())
                .build();
    }

    public static Gson getGson() {
        return getCommonBuilder()
                .create();
    }

    private static GsonBuilder getCommonBuilder() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
    }

    private static HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static Interceptor provideAuthInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {

                SharedPreferences preferences = App.getPreferences();

                String token = preferences.getString(TOKEN, "");


                Request.Builder auth = chain.request().newBuilder();

                if (!token.isEmpty()) {
                    auth.addHeader("authorization", token);
                }

                auth.addHeader("authorizationStatic", "nodeFushend");

                return chain.proceed(auth.build());
            }
        };
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideLoggingInterceptor())
                .addInterceptor(provideAuthInterceptor())
                .build();
    }
}
