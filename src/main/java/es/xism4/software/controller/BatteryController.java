package es.xism4.software.controller;

import es.xism4.software.manager.BatteryManager;

public class BatteryController {
    private final BatteryManager batteryManager;

    public BatteryController(BatteryManager batteryManager) {
        this.batteryManager = batteryManager;
    }

    public void balance() {
        System.out.println("Starting to balance cells");
        batteryManager.balanceCells();
        batteryManager.getCells().forEach(cell -> System.out.println());
    }
}