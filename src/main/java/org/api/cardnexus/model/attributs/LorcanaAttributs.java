package org.api.cardnexus.model.attributs;

import java.util.List;

import org.api.cardnexus.model.AbstractCardAttributs;

public class LorcanaAttributs extends AbstractCardAttributs {

    	private List<String> color;
    	private String type;
    	private int cost;
    	private int lore;
    	private int attack;
    	private int defense;
    	private String ink;
    	private List<String> traits;
    	private String cardSetId;
    	private String dreambornId;
    	private String description;
    	
    	
	public List<String> getColor() {
	    return color;
	}
	public String getType() {
	    return type;
	}
	public int getCost() {
	    return cost;
	}
	public int getLore() {
	    return lore;
	}
	public int getAttack() {
	    return attack;
	}
	public int getDefense() {
	    return defense;
	}
	public String getInk() {
	    return ink;
	}
	public List<String> getTraits() {
	    return traits;
	}
	public String getCardSetId() {
	    return cardSetId;
	}
	public String getDreambornId() {
	    return dreambornId;
	}
	public String getDescription() {
	    return description;
	}
    	
    	
    	
}
