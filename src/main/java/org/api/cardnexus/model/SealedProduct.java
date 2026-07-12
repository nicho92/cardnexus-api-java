package org.api.cardnexus.model;

import java.util.Map;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketCurrency;
import org.api.cardnexus.model.enums.EnumSealedType;

public class SealedProduct extends AbstractProduct {

    private String ean;
    private EnumSealedType productCategory;
    private Map<EnumMarketCurrency,MarketPrice> prices;
    
    public String getEan() {
        return ean;
    }
    public EnumSealedType getProductCategory() {
        return productCategory;
    }
    public MarketPrice getPrices(EnumFinishes f,EnumMarketCurrency currency) {
        return prices.get(currency);
    }
    
    
}
