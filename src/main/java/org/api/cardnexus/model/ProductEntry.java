package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class ProductEntry
{
    private int productId;
    private int quantity;
    private EnumCondition minCondition;
    private EnumFinishes finish;
    private List<String> languages;
    private Amount maxUnitPrice;
    
    
    public ProductEntry() {
	
    }
    
    
    
    public ProductEntry(int productId, int quantity, EnumCondition minCondition, EnumFinishes finish,List<String> languages, Amount maxUnitPrice) {
	super();
	this.productId = productId;
	this.quantity = quantity;
	this.minCondition = minCondition;
	this.finish = finish;
	this.languages = languages;
	this.maxUnitPrice = maxUnitPrice;
    }



    public int productId() {
	return productId;
    }
    
    public int quantity() {
	return quantity;
    }
    
    public Amount maxUnitPrice() {
	return maxUnitPrice;
    }
    public EnumCondition minCondition() {
	return minCondition;
    }
    public EnumFinishes finish() {
	return finish;
    }
    public List<String> languages() {
	return languages;
    }
        
    
    public void setFinish(EnumFinishes finish) {
	this.finish = finish;
    }
    
    public void setLanguages(List<String> languages) {
	this.languages = languages;
    }
    public void setMaxUnitPrice(Amount maxUnitPrice) {
	this.maxUnitPrice = maxUnitPrice;
    }
    public void setMinCondition(EnumCondition minCondition) {
	this.minCondition = minCondition;
    }
    public void setProductId(int productId) {
	this.productId = productId;
    }
    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }
    
    
}