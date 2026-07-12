package org.api.cardnexus.model;

import java.util.Date;

public class CardNexusPrice {

    private Amount low;
    private Integer listingCount;
    private Integer availableQuantity;
    private Date date;
    
    
    @Override
    public String toString() {
       return low.toString();
    }
    
    public Amount getLow() {
        return low;
    }
    public void setLow(Amount low) {
        this.low = low;
    }
    public Integer getListingCount() {
        return listingCount;
    }
    public void setListingCount(Integer listingCount) {
        this.listingCount = listingCount;
    }
    public Integer getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
}
