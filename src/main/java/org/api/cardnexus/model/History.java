package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;

public class History {
    
    private Date date;
    private EnumMarketPlace marketplace;
    private EnumFinishes finish;
    
    private Double low;
    private Double mid;
    private Double high;
    private Double marketValue;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public EnumMarketPlace getMarketplace() {
        return marketplace;
    }
    public void setMarketplace(EnumMarketPlace marketplace) {
        this.marketplace = marketplace;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public void setFinish(EnumFinishes finish) {
        this.finish = finish;
    }
    public Double getLow() {
        return low;
    }
    public void setLow(Double low) {
        this.low = low;
    }
    public Double getMid() {
        return mid;
    }
    public void setMid(Double mid) {
        this.mid = mid;
    }
    public Double getHigh() {
        return high;
    }
    public void setHigh(Double high) {
        this.high = high;
    }
    public Double getMarketValue() {
        return marketValue;
    }
    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }
    
    
    
}
