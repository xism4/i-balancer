package es.xism4.software.manager;

import es.xism4.software.data.BatteryCell;
import team.unnamed.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

public class BatteryManager {

    @Inject private List<BatteryCell> cells;
    @Inject private Logger logger;

    public void updateCellData(int index, double voltage, double temperature) {
        if (index < 0 || index >= cells.size()) {
            logger.warning("Out of bounds?: " + index);
            return;
        }

        BatteryCell cell = cells.get(index);

        if (cell.getTemperature() > 50) {
            logger.warning("High temperature on the cell" + index + ": " + cell.getTemperature());
            return;
        }

        cell.setVoltage(voltage);
        cell.setTemperature(temperature);
        logger.info("Updated cell" + index + " voltage: " + voltage + " temperature: " + temperature);
    }

    public void balanceCells() {
        double avgVoltage = cells.stream()
                .mapToDouble(BatteryCell::getVoltage)
                .average()
                .orElse(0);

        logger.info("Typical voltage: " + avgVoltage);

        for (BatteryCell cell : cells) {
            if (cell.getVoltage() > avgVoltage + 0.05) {
                logger.info("Balancing high difVol: " + cell.getVoltage());
                cell.setVoltage(avgVoltage);
            }
        }
    }

    @SuppressWarnings("unused")
    public List<BatteryCell> getCells() {
        return cells;
    }
}
