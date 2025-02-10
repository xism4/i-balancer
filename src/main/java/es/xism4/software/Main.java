package es.xism4.software;

import es.xism4.software.controller.BatteryController;
import es.xism4.software.data.Receiver;
import es.xism4.software.manager.BatteryManager;

import team.unnamed.inject.Inject;
import team.unnamed.inject.Injector;

import java.util.logging.Logger;

public class Main {

    @Inject private BatteryManager batteryManager;
    @Inject private Receiver dataReceiver;
    @Inject private BatteryController balanceController;
    @Inject private Logger logger;

    public static void main(String[] args) {
        Injector injector = Injector.create();
        Main main = injector.getInstance(Main.class);
        main.start();
    }

    public void start() {
        logger.info("Sistema de balanceo de baterías iniciado.");

        Thread receiverThread = new Thread(dataReceiver::startReceivingData);
        receiverThread.start();

        balanceController.balance();
    }

}
