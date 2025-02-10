package es.xism4.software;

import es.xism4.software.controller.BatteryController;
import es.xism4.software.data.Receiver;
import es.xism4.software.manager.BatteryManager;

public class Main {
    public static void main(String[] args) {
        BatteryManager batteryManager = new BatteryManager(5);
        Receiver dataReceiver = new Receiver("COM3", batteryManager);
        Thread receiverThread = new Thread(dataReceiver::startReceivingData);
        receiverThread.start();

        BatteryController balanceController = new BatteryController(batteryManager);
        balanceController.balance();
    }
}
