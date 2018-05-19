package ada.osc.myfirstweatherapp;

public class Wind {
    private final double speed;
    private final double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
