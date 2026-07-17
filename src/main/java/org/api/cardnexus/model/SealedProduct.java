package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumSealedType;

public class SealedProduct extends AbstractProduct {

    private String ean;
    private EnumSealedType productCategory;
    private ProductPriceMarket prices;
    
    public String getEan() {
        return ean;
    }
    public EnumSealedType getProductCategory() {
        return productCategory;
    }
    public ProductPriceMarket getPrices() {
	return prices;
    }
    
    
}
