package org.api.cardnexus.model;

public class Fee {
    
    private Double percentage;
    private Amount amount;
    
    public Double getPercentage() {
        return percentage;
    }
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
    public Amount getAmount() {
        return amount;
    }
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    
    
}
