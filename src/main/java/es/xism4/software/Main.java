package es.xism4.software;

import es.xism4.software.controller.BatteryController;
import es.xism4.software.data.Receiver;
import es.xism4.software.manager.BatteryManager;

import team.unnamed.inject.Inject;
import team.unnamed.inject.Injector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Main {

    @Inject private BatteryManager batteryManager;
    @Inject private Receiver dataReceiver;
    @Inject private BatteryController balanceController;
    @Inject private Logger logger;

    private ExecutorService executorService;

    public static void main(String[] args) {
        Injector injector = Injector.create();
        Main main = injector.getInstance(Main.class);
        main.start();
    }

    public void start() {
        logger.info("Lithium battery balancer");

        executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                dataReceiver.startReceivingData();
            } catch (Exception e) {
                logger.severe("Error while data receiving: " + e.getMessage());
            }
        });

        balanceController.balance();
    }

    @SuppressWarnings("unused")
    public void stop() {
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
