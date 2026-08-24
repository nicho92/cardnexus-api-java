package org.api.cardnexus.model.attributs;

import java.util.List;

import org.api.cardnexus.model.AbstractCardAttributs;

public class FabAttributs extends AbstractCardAttributs {

    
    private List<String> classes;
    private List<String> talents;
    private List<String> types;
    private List<String> subTypes;
    private String fabId;
    private int cost;
    private String pitch;
    private int attack;
    private int defense;
    private int intellect;
    private Integer life;
    private String description;
    private String artist;
    
    
    
    public List<String> getClasses() {
        return classes;
    }
    public List<String> getTalents() {
        return talents;
    }
    public List<String> getTypes() {
        return types;
    }
    public List<String> getSubTypes() {
        return subTypes;
    }
    public String getFabId() {
        return fabId;
    }
    public int getCost() {
        return cost;
    }
    public String getPitch() {
        return pitch;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getIntellect() {
        return intellect;
    }
    public Integer getLife() {
        return life;
    }
    public String getDescription() {
        return description;
    }
    public String getArtist() {
        return artist;
    }
    
    
    
    
}
