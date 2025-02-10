package es.xism4.software.controller;

import es.xism4.software.manager.BatteryManager;
import team.unnamed.inject.Inject;

public class BatteryController {
    @Inject private BatteryManager batteryManager;

    public void balance() {
        System.out.println("Starting to balance cells");
        batteryManager.balanceCells();
    }
}