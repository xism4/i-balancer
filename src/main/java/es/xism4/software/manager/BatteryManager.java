package es.xism4.software.manager;

import es.xism4.software.data.BatteryCell;

import java.util.List;
import java.util.ArrayList;

public class BatteryManager {
    private final List<BatteryCell> cells;

    public BatteryManager(int numCells) {
        cells = new ArrayList<>();
        for (int i = 0; i < numCells; i++) {
            cells.add(new BatteryCell(0.0, 0.300));
        }
    }

    public List<BatteryCell> getCells() {
        return cells;
    }

    public void updateCellData(int index, double voltage, double temperature) {
        BatteryCell cell = cells.get(index);

        if(cell.getTemperature() > 300) {
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
