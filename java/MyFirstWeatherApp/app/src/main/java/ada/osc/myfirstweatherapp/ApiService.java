package ada.osc.myfirstweatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeather(@Query("appid") String apiKey, @Query("q") String city);
}
