package org.api.cardnexus.model;

public class Location {
    private String color;
    private String name;
    private String icon;
    
    
    @Override
    public String toString() {
      return getName();
    }
    
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    
    
}
