package org.api.cardnexus.model;

import java.util.Date;

public class Payout {

    private Amount amount;
    private Date eligibleAt;
    private Date paidOutAt;
    
    
    public Amount getAmount() {
        return amount;
    }
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    public Date getEligibleAt() {
        return eligibleAt;
    }
    public void setEligibleAt(Date eligibleAt) {
        this.eligibleAt = eligibleAt;
    }
    public Date getPaidOutAt() {
        return paidOutAt;
    }
    public void setPaidOutAt(Date paidOutAt) {
        this.paidOutAt = paidOutAt;
    }
    
    
    
}
