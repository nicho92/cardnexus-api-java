package org.api.cardnexus.model;

import java.util.List;

public class CardAttributs {

   private List<String> color;
   private List<String> colorIdentity;
   private List<String> types;
   private String description;
   
    public List<String> getColor() {
        return color;
    }
    public void setColor(List<String> color) {
        this.color = color;
    }
    public List<String> getColorIdentity() {
        return colorIdentity;
    }
    public void setColorIdentity(List<String> colorIdentity) {
        this.colorIdentity = colorIdentity;
    }
    public List<String> getTypes() {
        return types;
    }
    public void setTypes(List<String> types) {
        this.types = types;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
