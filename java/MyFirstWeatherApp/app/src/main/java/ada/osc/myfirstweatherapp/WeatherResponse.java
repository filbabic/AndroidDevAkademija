package ada.osc.myfirstweatherapp;

public class WeatherResponse {
    private Weather[] weather = new Weather[1];
    private Main main;
    private Wind wind;
    private String dt_txt;
    private String cityName;

    public WeatherResponse(Weather[] weather, Main main, Wind wind, String dt_txt, String cityName) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.dt_txt = dt_txt;
        this.cityName = cityName;
    }

    public WeatherResponse() {
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Weather getWeatherObject() {
        return weather[0];
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setWeatherObject(Weather weatherObject) {
        this.weather[0] = weatherObject;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
