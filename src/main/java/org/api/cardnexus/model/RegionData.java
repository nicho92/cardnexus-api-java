package org.api.cardnexus.model;

import java.util.Map;

import org.api.cardnexus.model.enums.EnumCondition;

public class RegionData extends PriceStockValue{

    private String currency;
    private Map<EnumCondition, PriceStockValue> byCondition;
    
    
    @Override
    public String toString() {
        return getCurrency() + " " + byCondition;
    }
    
    
    
    public String getCurrency() {
	return currency;
    }
    
    public Map<EnumCondition, PriceStockValue> getByCondition() {
	return byCondition;
    }
}
