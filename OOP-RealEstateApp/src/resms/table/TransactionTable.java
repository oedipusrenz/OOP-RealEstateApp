/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resms.table;

import resms.transactions.TransactionReport;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author u1ben
 */
public class TransactionTable extends AbstractTableModel implements TableModel {

    private final ArrayList<TransactionReport> reports;
    private final String[] columnNames = {"Property Code", "Property Type", "Price", "Customer Name", "Contact No", "Transaction Type", "Pay Method"};

    public TransactionTable (ArrayList<TransactionReport> reports) {
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
        TransactionReport report = reports.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return report.getLot().getPropertyCode();
            case 1:
                return report.getLot().getPropertyType();
            case 2: 
                return report.getLot().getPrice();
            case 3:
                return report.getCustomerName();
            case 4: 
                return report.getCustomerContact();
            case 5:
                return report.getType();
            case 6:
                return report.getPayMethod();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }

    @Override
    public TransactionReport getReportAt(int rowIndex) {
        return reports.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
