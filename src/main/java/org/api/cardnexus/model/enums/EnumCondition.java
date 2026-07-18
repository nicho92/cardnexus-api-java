package org.api.cardnexus.model.enums;

public enum EnumCondition {
    NM("Near Mint"), 
    LP("Lightly Played"), 
    MP("Moderately Played"), 
    HP("Heavily Played"), 
    DMG("Damaged");
    
    
    private String label;

    EnumCondition(String value)
    {
	this.label=value;
    }
    
    public String getLabel() {
	return label;
    }
    
    
    
}
