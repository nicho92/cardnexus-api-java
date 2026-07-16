package org.api.cardnexus.model;

public record Tag (String color,String name,String icon)
{
    
    @Override
    public String toString() {
      return name();
    }
    
}
