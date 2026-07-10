package org.api.cardnexus.services.model;

public enum EnumMarketPlace {

    CARDMARKET, TCGPLAYER;
    
    
    public String toValue()
    {
	return name().toLowerCase();
    }
    
    
}
