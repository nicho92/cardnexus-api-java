package org.api.cardnexus.model.attributs;

import java.util.List;

import org.api.cardnexus.model.AbstractCardAttributs;

public class MTGAttributs extends AbstractCardAttributs {
    
    private List<String> color;
    private List<String> colorIdentity;
    private List<String> types;
    private String description;
    
    
    public List<String> getColor() {
        return color;
    }
    public List<String> getColorIdentity() {
        return colorIdentity;
    }
    public List<String> getTypes() {
        return types;
    }
    public String getDescription() {
        return description;
    }
    
    
    
}
