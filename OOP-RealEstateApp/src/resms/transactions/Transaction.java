package resms.transactions;

import resms.lot.Lot;

public class Transaction {
    private Lot lot; 
    private String transactionType;
    private String paymentMethod;
    private String customerName;
    private String customerContact;
    private double finalPrice;

    public Transaction(Lot lot, String transactionType, String paymentMethod, String customerName, String customerContact, double finalPrice) {
        this.lot = lot;
        this.transactionType = transactionType;
        this.paymentMethod = paymentMethod;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.finalPrice = finalPrice;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public double calculatePrice() {
        return lot.calculatePrice(transactionType);
    }
}

