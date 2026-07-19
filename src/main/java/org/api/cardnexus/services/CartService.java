package org.api.cardnexus.services;

import java.io.IOException;

import org.api.cardnexus.model.Cart;
import org.api.cardnexus.model.MarketList;
import org.api.cardnexus.model.Run;
import org.api.cardnexus.model.enums.EnumKindsRun;
import org.api.cardnexus.model.requests.CardOptimizationRequest;
import org.api.cardnexus.model.requests.CartAddingRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CartService extends AbstractNexusService {

    
    public String runOptimizationQuery(CardOptimizationRequest req) throws IOException
    {
	return client.post(ROOT_OPTIMIZER_ENDPOINT+"/runs", req, JsonObject.class).get("id").getAsString();
    }
    
    public Cart applyRunsToCart(String runId, EnumKindsRun mode) throws IOException
    {
	var obj = new JsonObject();
		obj.addProperty("mode", mode.name());
	
	return client.post(ROOT_OPTIMIZER_ENDPOINT+"/runs/"+runId+"/apply", obj, Cart.class);
    }
    
    
    public Run getRunById(String id) throws IOException
    {
	return client.get(ROOT_OPTIMIZER_ENDPOINT+"/runs/"+id, Run.class);
    }
    
    public Cart getYourCart() throws IOException
    {
	return client.get(ROOT_CART_ENDPOINT, Cart.class);
    }
    
    public Cart clearCart() throws IOException
    {
	return client.delete(ROOT_CART_ENDPOINT, null,Cart.class);
    }
    
    public Cart removeItem(String listingId) throws IOException
    {
	return client.delete(ROOT_CART_ENDPOINT+"/items/"+listingId, null,Cart.class);
    }
    
    public Cart removeItem(MarketList list) throws IOException
    {
	return removeItem(list.listingId());
    }
    
    public Cart addItem(CartAddingRequest req) throws IOException
    {
	
	var obj = new JsonObject();
		obj.addProperty("deliveryCountry", req.getDeliveryCountry());
	var arr = new JsonArray();
		obj.add("items", arr);
	
		req.getItems().entrySet().forEach(e->{
		    var it = new JsonObject();
		    	it.addProperty("listingId", e.getKey());
		    	it.addProperty("quantity", e.getValue());
		    	arr.add(it);
		});
		
	
	return client.post(ROOT_CART_ENDPOINT+"/items", obj,Cart.class);
    }
    
}
