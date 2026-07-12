package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardNexusPrice;
import org.api.cardnexus.model.History;
import org.api.cardnexus.model.MarketVariations;
import org.api.cardnexus.model.Price;
import org.api.cardnexus.model.Sales;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.requests.HistoryRequest;

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
	        if(m.getKey().equals(EnumMarketPlace.cardmarket.name()))
	    		p.getCardMarket().put(finish, client.fromJson(m.getValue().toString(), MarketVariations.class));

	        if(m.getKey().equals(EnumMarketPlace.tcgplayer.name()))
	    		p.getTcgplayer().put(finish, client.fromJson(m.getValue().toString(), MarketVariations.class));
	    	    
	        if(m.getKey().equals("cardnexus"))
	    		p.getCardNexus().put(finish, client.fromJson(m.getValue().toString(), CardNexusPrice.class));
	    });
	    
	});
	return p;
    }
    
    
    public List<History> getHistoryPrice(HistoryRequest req) throws IOException
    {
	return client.getPaginated(ROOT_PRODUCT_ENDPOINT+"/"+req.getIdProduct()+"/prices/history?"+req.toQueryString(), null, History.class).getData();
	    
    }
    
    
    public List<Sales> getLastSales(AbstractProduct product) throws IOException
    {
	return getLastSales(product.getId());
    }
    
    public List<Sales> getLastSales(Integer productId) throws IOException
    {
	var ret = new ArrayList<Sales>();
	var result = client.getPaginated(ROOT_PRODUCT_ENDPOINT+"/"+productId+"/sales" ,null,Sales.class);
	    ret.addAll(result.getData());
	return ret;
    }
    
    
    
    
    
    
    
}
