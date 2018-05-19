package ada.osc.myfirstweatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment {

    private TextView mCurrentTemperature;
    private TextView mMinTemperature;
    private TextView mMaxTemperature;
    private TextView mPressure;
    private TextView mWind;
    private TextView mDescription;
    private ImageView mWeatherIcon;

    public static WeatherFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        WeatherFragment f = new WeatherFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        String cityToDisplay = getArguments().getString(Constants.CITY_BUNDLE_KEY);

        App.getRetrofit().create(ApiService.class).getWeather(Constants.APP_ID, cityToDisplay).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response != null && response.body() != null) {
                    WeatherResponse data = response.body();

                    setCurrentTemperatureValues(data.getMain().getTemp_max());
                    setPressureValues(data.getMain().getPressure());
                    setDescriptionValues(data.getWeatherObject().getDescription());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }

    private void initUI(View view) {
        mCurrentTemperature = (TextView) view.findViewById(R.id.weather_display_current_temperature_text_view);
        mMinTemperature = (TextView) view.findViewById(R.id.weather_fragment_min_temperature_text_view);
        mMaxTemperature = (TextView) view.findViewById(R.id.weather_fragment_max_temperature_text_view);
        mPressure = (TextView) view.findViewById(R.id.weather_display_pressure_text_view);
        mWind = (TextView) view.findViewById(R.id.weather_display_wind_text_view);
        mDescription = (TextView) view.findViewById(R.id.weather_display_detailed_description_text_view);
        mWeatherIcon = (ImageView) view.findViewById(R.id.weather_display_weather_icon_image_view);
    }

    public void setCurrentTemperatureValues(double temperatureValues) {
        mCurrentTemperature.setText(getString(R.string.current_temperature_message, temperatureValues));
    }

    public void setMinTemperatureValues(double minTemperatureValues) {
        mMinTemperature.setText(getString(R.string.minimum_temperature_message, minTemperatureValues));
    }

    public void setMaxTemperatureValues(double maxTemperatureValues) {
        mMaxTemperature.setText(getString(R.string.maximum_temperature_message, maxTemperatureValues));
    }

    public void setPressureValues(double pressureValues) {
        mPressure.setText(getString(R.string.pressure_message, pressureValues));

    }

    public void setWindValues(double windValues) {
        mWind.setText(getString(R.string.wind_speed_message, windValues));
    }

    public void setWeatherIcon(String iconPath) {
        Glide.with(getActivity().getApplicationContext()).load(Constants.IMAGE_BASE_URL + iconPath).into(mWeatherIcon);
    }

    public void setDescriptionValues(String descriptionValues) {
        mDescription.setText(descriptionValues);
    }


    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_loading_failure_toast_message), Toast.LENGTH_SHORT).show();
    }

    private void refreshCurrentData() {
        if (NetworkUtils.checkIfInternetConnectionIsAvailable(getActivity())) {
        }
    }
}
