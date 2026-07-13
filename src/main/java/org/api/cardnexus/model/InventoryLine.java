package org.api.cardnexus.model;

import java.util.Date;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class InventoryLine {

    
    private String id;
    private String customId;
    private String comment;
    private Integer productId;
    private String game;
    private EnumFinishes finish;
    private EnumCondition condition;
    private String language;
    private Integer quantity;
    private boolean forSale;
    private Date updatedAt;
    private Map<String,Amount> listings; 
    private Graded graded;
    
    
    
    public Graded getGraded() {
        return graded;
    }
    public void setGraded(Graded graded) {
        this.graded = graded;
    }
    public Map<String, Amount> getListings() {
        return listings;
    }
    public void setListings(Map<String, Amount> listings) {
        this.listings = listings;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCustomId() {
        return customId;
    }
    public void setCustomId(String customId) {
        this.customId = customId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public void setFinish(EnumFinishes finish) {
        this.finish = finish;
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
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public boolean isForSale() {
        return forSale;
    }
    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    
    
}
