package org.api.cardnexus.model;

public class PriceStockValue {
    private Double low;
    private Integer listingCount;
    private Integer availableQuantity;
    
    
    
    @Override
    public String toString() {
        return getLow() + " "+ getAvailableQuantity();
    }
    
    
    public Integer getListingCount() {
	return listingCount;
    }
    
    public Double getLow() {
	return low;
    }
    
    public Integer getAvailableQuantity() {
	return availableQuantity;
    }
    
}
