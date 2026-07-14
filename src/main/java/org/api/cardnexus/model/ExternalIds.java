package org.api.cardnexus.model;

import java.util.List;

public class ExternalIds {
    private String scryfallId;
    private String scryfallOracleId;
    
    private List<ExtId> cardmarket;
    private List<ExtId> tcgplayer;
    
    
    public List<ExtId> getCardmarket() {
	return cardmarket;
    }
    
    public List<ExtId> getTcgplayer() {
	return tcgplayer;
    }
    
    public String getScryfallId() {
	return scryfallId;
    }
    
    public String getScryfallOracleId() {
	return scryfallOracleId;
    }
    
}
