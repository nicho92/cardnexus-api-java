package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class ListItemRequestData {

    String itemId;
    Integer productId;
    EnumFinishes finish;
    String language;
    int quantity;
    EnumCondition minCondition;
    double wantPrice;
    double sellPrice;
    
    public ListItemRequestData() {
	
    }
    
    
    
    public ListItemRequestData(String itemId, Integer productId, EnumFinishes finish, String language, int quantity,EnumCondition minCondition, double wantPrice, double sellPrice) {
	this.itemId = itemId;
	this.productId = productId;
	this.finish = finish;
	this.language = language;
	this.quantity = quantity;
	this.minCondition = minCondition;
	this.wantPrice = wantPrice;
	this.sellPrice = sellPrice;
    }



    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public EnumCondition getMinCondition() {
        return minCondition;
    }
    public void setMinCondition(EnumCondition minCondition) {
        this.minCondition = minCondition;
    }
    public double getWantPrice() {
        return wantPrice;
    }
    public void setWantPrice(double wantPrice) {
        this.wantPrice = wantPrice;
    }
    public double getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
    
    
}
