package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.configuration.NexusConstants;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.Product;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.interfaces.AbstractNexusService;

import com.google.gson.JsonObject;

public class ProductsService extends AbstractNexusService{

    
    private static final String ROOT_GAME_ENDPOINT="/games";
    private static final String ROOT_PRODUCT_ENDPOINT="/products";
    
    
    
    public List<Game> listGames() throws IOException
    {
	var arr = client.get(ROOT_GAME_ENDPOINT, null, JsonObject.class).get("data").getAsJsonArray();
	return client.toList(arr, Game.class);
    }
    
    public Game getGameById(String id) throws IOException
    {
	return client.get(ROOT_GAME_ENDPOINT+id, null, Game.class);
    }
    

    public List<Expansion> listExpansion(Game game) throws IOException
    {
	return listExpansion(game.getId());
    }
    
    public List<Expansion> listExpansion(String id) throws IOException
    {
	var ret = new ArrayList<Expansion>();
	
	var result =  client.getPaginated(ROOT_GAME_ENDPOINT+"/"+id+"/expansions?offset="+ret.size()+"&limit="+NexusConstants.LIMIT_LIST_RESULTS, null, Expansion.class);
	
	ret.addAll(result.getData());
	
	
	while(result.getPagination().hasMore())
	{
	    result = client.getPaginated(ROOT_GAME_ENDPOINT+"/"+id+"/expansions?offset="+ret.size()+"&limit="+NexusConstants.LIMIT_LIST_RESULTS, null, Expansion.class);
	    ret.addAll(result.getData());
	}
	
	return ret;
    }
    
    public List<Product> searchProduct(String name) throws IOException
    {
	var req = new SearchProductRequest();
	
	req.getCardmarketId().add(1);
	req.setExpansionId(42);
	req.addGameFilter("game", "mtg");
	req.setSortBy("printNumber");
	req.setSortDirection("asc");
	
	var page = client.postPaginated(ROOT_PRODUCT_ENDPOINT+"/search",req,null,Product.class);
	
	
	return new ArrayList<>();
	
    }
    
    
    
    
    
    
}
