package org.api.cardnexus.model;

import java.util.EnumMap;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumFinishes;

public class Price {
    private Integer productId;
    
    private Map<EnumFinishes, MarketVariations> cardMarket;
    private Map<EnumFinishes, MarketVariations> tcgplayer;
    private Map<EnumFinishes, CardNexusPrice> cardnexus;
    
    
    
    public Price() {
	cardMarket = new EnumMap<>(EnumFinishes.class);
	tcgplayer= new EnumMap<>(EnumFinishes.class);
	cardnexus= new EnumMap<>(EnumFinishes.class);
    }
    
    public Map<EnumFinishes, MarketVariations> getCardMarket() {
	return cardMarket;
    }
    
    public Map<EnumFinishes, MarketVariations> getTcgplayer() {
	return tcgplayer;
    }
    
    public Integer getProductId() {
	return productId;
    }
    
    public void setProductId(Integer productId) {
	this.productId = productId;
    }
    
    public Map<EnumFinishes, CardNexusPrice> getCardNexus() {
	return cardnexus;
    }
    
    public void setCardNexus(Map<EnumFinishes, CardNexusPrice> cardnexus) {
	this.cardnexus = cardnexus;
    }
    
    
}
