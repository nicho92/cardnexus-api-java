package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class ListItem {
    private String id;
    private Integer productId;
    private String name;
    private String nameSlug;
    private String expansion;
    private EnumFinishes finish;
    private String language;
    private EnumCondition minCondition;
    private Integer quantity;
    private Integer quantityFulfilled;
    private Double wantPrice;
    private Double sellPrice;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNameSlug() {
        return nameSlug;
    }
    public void setNameSlug(String nameSlug) {
        this.nameSlug = nameSlug;
    }
    public String getExpansion() {
        return expansion;
    }
    public void setExpansion(String expansion) {
        this.expansion = expansion;
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
    public EnumCondition getMinCondition() {
        return minCondition;
    }
    public void setMinCondition(EnumCondition minCondition) {
        this.minCondition = minCondition;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getQuantityFulfilled() {
        return quantityFulfilled;
    }
    public void setQuantityFulfilled(Integer quantityFulfilled) {
        this.quantityFulfilled = quantityFulfilled;
    }
    public Double getWantPrice() {
        return wantPrice;
    }
    public void setWantPrice(Double wantPrice) {
        this.wantPrice = wantPrice;
    }
    public Double getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
    
    
    
    
}
