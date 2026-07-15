package org.api.cardnexus.model;

public record Location (String color,String name,String icon)
{
    
    
    
    @Override
    public String toString() {
      return name();
    }
    
        
}
