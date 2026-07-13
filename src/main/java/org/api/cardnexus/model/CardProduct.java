package org.api.cardnexus.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketCurrency;
import org.api.cardnexus.model.enums.EnumRarity;

public class CardProduct extends AbstractProduct {

    private String printNumber;
    private EnumRarity rarity;
    private List<EnumFinishes> finishes;
    private Map<EnumFinishes,Map<EnumMarketCurrency,MarketVariations>> pricesByFinish;
    private CardAttributs attributes;
    
    
    
    public CardProduct() {
	pricesByFinish = new EnumMap<>(EnumFinishes.class);
    }
    
    public CardAttributs getAttributes() {
	return attributes;
    }
    public MarketVariations getPrices(EnumFinishes finish,EnumMarketCurrency currency)   {
		var a = pricesByFinish.get(finish);
		
		if(a==null)
		    return null;
		
		return a.get(currency);
    }
    public String getPrintNumber() {
        return printNumber;
    }
    public EnumRarity getRarity() {
        return rarity;
    }
    public List<EnumFinishes> getFinishes() {
        return finishes;
    }
    
}
