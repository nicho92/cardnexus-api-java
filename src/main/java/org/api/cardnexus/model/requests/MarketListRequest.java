package org.api.cardnexus.model.requests;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class MarketListRequest extends AbstractGetRequest {

    private EnumCondition condition;
    private String language;
    private EnumFinishes finish;
    private String region;
    private String deliveryCountry;
    private Integer productId;
    
    private MarketListRequest()
    {
	
    }
    
    public static MarketListRequest create()
    {
	return new MarketListRequest();
    }
    
    
    public MarketListRequest setProductId(Integer productId) {
	this.productId = productId;
	return this;
    }
    public Integer getProductId() {
	return productId;
    }
    public EnumCondition getCondition() {
        return condition;
    }
    public MarketListRequest setCondition(EnumCondition condition) {
        this.condition = condition;
        return this;
    }
    public String getLanguage() {
        return language;
    }
    public MarketListRequest setLanguage(String language) {
        this.language = language;
        return this;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public MarketListRequest setFinish(EnumFinishes finish) {
        this.finish = finish;
        return this;
    }
    public String getRegion() {
        return region;
    }
    public MarketListRequest setRegion(String region) {
        this.region = region;
        return this;
    }
    public String getDeliveryCountry() {
        return deliveryCountry;
    }
    public MarketListRequest setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
        return this;
    }
    
    
    
}
