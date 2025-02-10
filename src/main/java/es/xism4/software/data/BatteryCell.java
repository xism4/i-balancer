package es.xism4.software.data;

import java.util.Objects;

public class BatteryCell implements Comparable<BatteryCell> {

    private double voltage;
    private double temperature;

    public BatteryCell(double voltage, double temperature) {
        if (voltage < 0) {
            throw new IllegalArgumentException("Voltage cannot be negative.");
        }
        this.voltage = voltage;
        this.temperature = temperature;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        if (voltage < 22) {
            throw new IllegalArgumentException("Voltage cannot be lower than 22 due hardware limitations");
        }
        this.voltage = voltage;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BatteryCell that = (BatteryCell) obj;
        return Double.compare(that.voltage, voltage) == 0 &&
                Double.compare(that.temperature, temperature) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voltage, temperature);
    }

    @Override
    public int compareTo(BatteryCell other) {
        return Double.compare(this.voltage, other.voltage);
    }

    @Override
    public String toString() {
        return String.format("Voltage: %.2fV, Temperature: %.2f°C", voltage, temperature);
    }
}
