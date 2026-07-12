package org.api.cardnexus.model.account;

import java.util.Date;

public class Balance {

    private Date updatedAt;
    private Amount available;
    private Amount pending;
    
    @Override
    public String toString() {
        return String.valueOf(getAvailable());
    }
    
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Amount getAvailable() {
        return available;
    }
    public void setAvailable(Amount available) {
        this.available = available;
    }
    public Amount getPending() {
        return pending;
    }
    public void setPending(Amount pending) {
        this.pending = pending;
    }
    
    
    
    
}
