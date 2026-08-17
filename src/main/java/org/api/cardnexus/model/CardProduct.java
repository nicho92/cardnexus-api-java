package org.api.cardnexus.model;

import java.util.List;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumRarity;
import org.api.cardnexus.model.enums.EnumVariant;


public class CardProduct extends AbstractProduct {

    private String printNumber;
    private EnumRarity rarity;
    private List<EnumFinishes> finishes;
    private Map<EnumFinishes, ProductPriceMarket> pricesByFinish;
    private CardAttributs attributes;
    private EnumVariant variant;

    
    
    
    @Override
    public int hashCode() {
        return getId();
    }
    
   @Override
   public boolean equals(Object obj) {
        if(obj instanceof CardProduct p)
            return p.getId()==getId();
        
        return false;
   }
    
   public Map<EnumFinishes, ProductPriceMarket> getPrices() {
       return pricesByFinish;
   }
   
   @Override
    public void setPrices(Map<EnumFinishes, ProductPriceMarket> map) {
           pricesByFinish = map;
    }
   
    public CardAttributs getAttributes() {
	return attributes;
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
    public EnumVariant getVariant() {
	return variant;
    }
    
}


record CardAttributs (List<String> colorIdentity, List<String> types,String description) {}
