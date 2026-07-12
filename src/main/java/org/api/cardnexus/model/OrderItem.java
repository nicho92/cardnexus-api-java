package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class OrderItem {

    private Integer productId;
    private String productName;
    private String imageUrl;
    private EnumCondition condition;
    private String language;
    private EnumFinishes finish;
    private Integer quantity;
    private Amount unitPrice;
    private Amount lineTotal;
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public EnumCondition getCondition() {
        return condition;
    }
    public void setCondition(EnumCondition condition) {
        this.condition = condition;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public void setFinish(EnumFinishes finish) {
        this.finish = finish;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Amount getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Amount unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Amount getLineTotal() {
        return lineTotal;
    }
    public void setLineTotal(Amount lineTotal) {
        this.lineTotal = lineTotal;
    }
    
    
}
