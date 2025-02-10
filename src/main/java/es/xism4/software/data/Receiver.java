package es.xism4.software.data;

import com.fazecast.jSerialComm.SerialPort;
import es.xism4.software.manager.BatteryManager;

import java.util.Scanner;

public class Receiver {
    private final SerialPort serialPort;
    private final BatteryManager batteryManager;

    public Receiver(String portName, BatteryManager batteryManager) {
        this.serialPort = SerialPort.getCommPort(portName);
        this.serialPort.setBaudRate(9600); //todo check config of ESP32
        this.batteryManager = batteryManager;
    }

    public void startReceivingData() {
        if (!serialPort.openPort()) {
            System.out.println("Error opening port!");
            return;
        }

        Scanner scanner = new Scanner(serialPort.getInputStream());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parseData(line);
        }
    }

    private void parseData(String data) {
        String[] parts = data.split(":");
        if (parts.length == 3) {
            int index = Integer.parseInt(parts[0]);
            double voltage = Double.parseDouble(parts[1]);
            double temperature = Double.parseDouble(parts[2]);
            batteryManager.updateCellData(index, voltage, temperature);
        }
    }
}