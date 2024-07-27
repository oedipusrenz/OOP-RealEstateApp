package resms.transactions;

import resms.lot.Lot;



public class TransactionReport {

    private final Lot lot;
    private final String type;
    private final String payMethod;
    private final String status;
    private final String customerName;    
    private final String customerContact;

    public TransactionReport(Transaction transaction) {
        this.lot = transaction.getLot();
        this.type = transaction.getTransactionType();
        this.payMethod = transaction.getPaymentMethod();
        this.status = lot.getStatus(); 
        this.customerName = transaction.getCustomerName();
        this.customerContact = transaction.getCustomerContact(); 
    }

    public Lot getLot() {
        return lot;
    }

    public String getType() {
        return type;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getStatus() {
        return status;
    }
    
    public String getCustomerName() { 
        return customerName;
    }

    public String getCustomerContact() { 
        return customerContact;
    }

    @Override
    public String toString() {
        return String.format("Property: %s, Type: %s, Payment Method: %s, Status: %s",
                             lot.getPropertyCode(), type, payMethod, status);
    }
}