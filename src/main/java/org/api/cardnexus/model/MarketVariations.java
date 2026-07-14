package org.api.cardnexus.model;

import java.util.Date;

public class MarketVariations {
    private Double low;
    private Double mid;
    private Double marketValue;
    private Double change24h;
    private Double change7d;
    private Double change30d;
    private Date date;
    
    @Override
    public String toString() {
	return String.valueOf(getMarketValue());
    }
    
    public Date getDate() {
	return date;
    }
    public void setDate(Date date) {
	this.date = date;
    }
    public double getLow() {
        return low;
    }
    public void setLow(double low) {
        this.low = low;
    }
    public double getMid() {
        return mid;
    }
    public void setMid(double mid) {
        this.mid = mid;
    }
    public double getMarketValue() {
        return marketValue;
    }
    public double getChange24h() {
        return change24h;
    }
    public double getChange7d() {
        return change7d;
    }
    public double getChange30d() {
        return change30d;
    }
    
}
