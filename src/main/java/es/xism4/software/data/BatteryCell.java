package es.xism4.software.data;

public class BatteryCell {
    private double voltage;
    private double temperature;

    public BatteryCell(double voltage, double temperature) {
        this.voltage = voltage;
        this.temperature = temperature;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Voltage: " + voltage + "V, Temperature: " + temperature + "°C";
    }
}
