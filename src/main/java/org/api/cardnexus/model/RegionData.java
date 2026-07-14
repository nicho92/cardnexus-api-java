package org.api.cardnexus.model;

import java.util.Map;

import org.api.cardnexus.model.enums.EnumCondition;

public class RegionData extends PriceStockValue{

    private String currency;
    private Map<EnumCondition, LangageData> byCondition;
        
    public String getCurrency() {
	return currency;
    }
    
    public Map<EnumCondition, LangageData> getByCondition() {
	return byCondition;
    }
}
