package ada.osc.taskie.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        return new
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient okHttpClient() {
        return new
                OkHttpClient.Builder().addInterceptor(provideLoggingInterceptor()).build();
    }
}
