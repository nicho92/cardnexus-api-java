package org.api.cardnexus.model.requests;

import java.util.HashMap;
import java.util.Map;

import org.api.cardnexus.model.MarketList;

public class CartAddingRequest {

    private Map<MarketList,Integer> items;
    private String deliveryCountry;
    
    
    public static CartAddingRequest create()
    {
	return new CartAddingRequest();
    }
    
    
    private CartAddingRequest() {
	items = new HashMap<>();
    }
    
    public CartAddingRequest setDeliveryCountry(String deliveryCountry) {
	this.deliveryCountry = deliveryCountry;
	return this;
    }
    public String getDeliveryCountry() {
	return deliveryCountry;
    }
    
    public CartAddingRequest addItem(MarketList data)
    {
	addItem(data,1);
	return this;
    }
    
    public CartAddingRequest addItem(MarketList list , Integer qty)
    {
	items.put(list, qty);
	return this;
    }
    
     public Map<MarketList, Integer> getItems() {
	return items;
    }
}

