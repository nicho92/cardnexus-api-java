package org.api.cardnexus.model.attributs;

import java.util.List;

import org.api.cardnexus.model.AbstractCardAttributs;

public class OnePieceAttributs extends AbstractCardAttributs {

    private List<String> color;
    private List<String> attribute;
    private String type;
    private int power;
    private int cost;
    private int counter;
    private List<String> subtypes;
    private String rulesText;
    
    
    public List<String> getColor() {
        return color;
    }
    public List<String> getAttribute() {
        return attribute;
    }
    public String getType() {
        return type;
    }
    public int getPower() {
        return power;
    }
    public int getCost() {
        return cost;
    }
    public int getCounter() {
        return counter;
    }
    public List<String> getSubtypes() {
        return subtypes;
    }
    public String getRulesText() {
        return rulesText;
    }
    
    
    
    
}
