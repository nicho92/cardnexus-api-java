package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumSealedType;
import org.api.cardnexus.tools.Slugifyer;

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
    
    @Override
    public String urlProduct() {
	  return "https://cardnexus.com/fr/explore/"+getGame().id()+"/"+Slugifyer.nameSlug(getExpansion().name())+"/sealed/"+getNameSlug()+"-"+getId();
	    
    }
    
    
}
