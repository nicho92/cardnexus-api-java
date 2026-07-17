package org.api.cardnexus.model.requests;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class MarketListRequest extends AbstractGetRequest {

    private EnumCondition condition;
    private String language;
    private EnumFinishes finish;
    private String region;
    private String deliveryCountry;
    private transient Integer productId;
    
    
    public void setProductId(Integer productId) {
	this.productId = productId;
    }
    public Integer getProductId() {
	return productId;
    }
    
    
    public EnumCondition getCondition() {
        return condition;
    }
    public void setCondition(EnumCondition condition) {
        this.condition = condition;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public void setFinish(EnumFinishes finish) {
        this.finish = finish;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getDeliveryCountry() {
        return deliveryCountry;
    }
    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }
    
    
    
}
