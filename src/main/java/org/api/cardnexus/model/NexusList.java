package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumStatus;

public class NexusList {

    private String id;
    private String name;
    private String game;
    private EnumStatus status;
    private String description;
    private double completionPercentage;
    private int itemCount;
    private int totalQuantity;
    
    public boolean isComplete()
    {
	return completionPercentage>=100;
    }
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public EnumStatus getStatus() {
        return status;
    }
    public void setStatus(EnumStatus status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getCompletionPercentage() {
        return completionPercentage;
    }
    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    
    
    
    
}
