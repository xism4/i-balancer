package es.xism4.software.manager;

import es.xism4.software.data.BatteryCell;
import team.unnamed.inject.Inject;

import java.util.List;

public class BatteryManager {

    @Inject private List<BatteryCell> cells;

    public void updateCellData(int index, double voltage, double temperature) {
        BatteryCell cell = cells.get(index);
        if (cell.getTemperature() > 300) {
            return;
        }
        cell.setVoltage(voltage);
        cell.setTemperature(temperature);
    }

    public void balanceCells() {
        double avgVoltage = cells.stream().mapToDouble(BatteryCell::getVoltage).average().orElse(0);
        for (BatteryCell cell : cells) {
            if (cell.getVoltage() > avgVoltage + 0.05) {
                cell.setVoltage(avgVoltage);
            }
        }
    }
}
