package org.api.cardnexus.model;

import java.util.Map;

public class LangageData extends PriceStockValue {

    @Override
    public String toString() {
        return getLow() + "  - " + getAvailableQuantity();
    }
    
    
    private Map<String, PriceStockValue> byLanguage;
    
    
    public Map<String, PriceStockValue> getByLanguage() {
	return byLanguage;
    }
}
