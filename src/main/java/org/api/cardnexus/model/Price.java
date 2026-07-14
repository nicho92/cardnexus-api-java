package org.api.cardnexus.model;

import java.util.Map;

import org.api.cardnexus.model.enums.EnumFinishes;

public class Price {
    private Integer productId;
    
    private Map<EnumFinishes, ProductPriceMarket> pricesByFinish;
    
    
    public Integer getProductId() {
	return productId;
    }
    
    public void setProductId(Integer productId) {
	this.productId = productId;
    }
    
    public Map<EnumFinishes, ProductPriceMarket> getPricesByFinish() {
	return pricesByFinish;
    }
    
    
}
