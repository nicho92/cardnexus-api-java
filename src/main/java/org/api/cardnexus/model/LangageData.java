package org.api.cardnexus.model;

import java.util.Map;

public record LangageData (Double low, Integer listingCount, Integer availableQuantity,Map<String, PriceStockValue> byLanguage) 
{
    
}


record PriceStockValue (Double low, Integer listingCount,Integer availableQuantity)
{
            
}