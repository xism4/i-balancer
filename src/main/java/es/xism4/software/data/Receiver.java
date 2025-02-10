package es.xism4.software.data;

import com.fazecast.jSerialComm.SerialPort;
import es.xism4.software.manager.BatteryManager;
import team.unnamed.inject.Inject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Receiver {

    private final SerialPort serialPort;

    @Inject private Logger logger;
    @Inject private BatteryManager batteryManager;

    public Receiver(String portName) {
        this.serialPort = SerialPort.getCommPort(portName);
        this.serialPort.setBaudRate(9600);
    }

    public void startReceivingData() {
        if (!serialPort.openPort()) {
            logger.severe("Error opening port!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseData(line);
            }
        } catch (Exception e) {
            logger.severe("Error while receiving data: " + e.getMessage());
        } finally {
            serialPort.closePort();
            logger.info("Serial port closed.");
        }
    }

    private void parseData(String data) {
        try {
            String[] parts = data.split(":");
            if (parts.length == 3) {
                int index = Integer.parseInt(parts[0]);
                double voltage = Double.parseDouble(parts[1]);
                double temperature = Double.parseDouble(parts[2]);
                batteryManager.updateCellData(index, voltage, temperature);
            } else {
                logger.warning("Invalid data format received: " + data);
            }
        } catch (NumberFormatException e) {
            logger.warning("Error parsing data: " + data + " | Error: " + e.getMessage());
        }
    }
}
