/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resms.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import resms.lot.LotAvailability;

/**
 *
 * @author u1ben
 */
public class LotTable extends AbstractTableModel implements TableModel {

    private final ArrayList<LotAvailability> reports;
    private final String[] columnNames = {"Block No.", "Lot No.", "Property Type", "Size (sqm)", "Price (php)", "Status"};

    public LotTable(ArrayList<LotAvailability> reports) {
        this.reports = reports;
    }

    @Override
    public int getRowCount() {
        return reports.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LotAvailability report = reports.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return report.getLot().getBlockNo();
            case 1:
                return report.getLot().getLotNo();
            case 2:
                return report.getLot().getPropertyType();
            case 3:
                return report.getLot().getSize();
            case 4:
                return report.getLot().getPrice();
            case 5:
                return report.getLot().getStatus();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }

    @Override
    public LotAvailability getReportAt(int rowIndex) {
        return reports.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
