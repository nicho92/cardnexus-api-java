package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class Sales {

    private Date soldAt;
    private String region;
    private int quantity;
    private EnumFinishes finish;
    private String language;
    private EnumCondition condition;
    private Amount price;
    private Amount priceEur;
    
    
    public Date getSoldAt() {
        return soldAt;
    }
    public void setSoldAt(Date soldAt) {
        this.soldAt = soldAt;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public void setFinish(EnumFinishes finish) {
        this.finish = finish;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public EnumCondition getCondition() {
        return condition;
    }
    public void setCondition(EnumCondition condition) {
        this.condition = condition;
    }
    public Amount getPrice() {
        return price;
    }
    public void setPrice(Amount price) {
        this.price = price;
    }
    public Amount getPriceEur() {
        return priceEur;
    }
    public void setPriceEur(Amount priceEur) {
        this.priceEur = priceEur;
    }
    
    
    
    
}
