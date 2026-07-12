package org.api.cardnexus.services;

import java.io.IOException;

import org.api.cardnexus.configuration.AbstractNexusService;
import org.api.cardnexus.model.CardNexusPrice;
import org.api.cardnexus.model.MarketVariations;
import org.api.cardnexus.model.Price;
import org.api.cardnexus.model.enums.EnumFinishes;

import com.google.gson.JsonObject;

public class PricesService extends AbstractNexusService {

    public Price getCurrentPrice(Integer idProduct) throws IOException
    {
	var obj=client.get(ROOT_PRODUCT_ENDPOINT+"/"+idProduct+"/prices", null, JsonObject.class);
	var p = new Price();
	
	p.setProductId(obj.get("productId").getAsInt());
	
	
	var finishes = obj.get("finishes").getAsJsonObject();
	finishes.entrySet().forEach(e->{
	    var finish = EnumFinishes.valueOf(e.getKey());
	    var markets = e.getValue().getAsJsonObject();
	    
	    	
	    	markets.entrySet().forEach(m->{
	    	    
	    	    if(m.getKey().equals("cardmarket"))
	    		p.getCardMarket().put(finish, client.fromJson(m.getValue().toString(), MarketVariations.class));

	    	    if(m.getKey().equals("tcgplayer"))
	    		p.getTcgplayer().put(finish, client.fromJson(m.getValue().toString(), MarketVariations.class));
	    	    
	    	    if(m.getKey().equals("cardnexus"))
	    		p.getCardNexus().put(finish, client.fromJson(m.getValue().toString(), CardNexusPrice.class));
	    	    
	    });
	    
	    
	    
	    
	    
	});
	
		
	return p;
	
    }
    
    
}
