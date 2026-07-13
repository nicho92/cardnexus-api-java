package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.Pagination;
import org.api.cardnexus.model.requests.SearchProductRequest;

public class ProductsService extends AbstractNexusService{

    public List<Game> listGames() throws IOException
    {
	var arr = client.getPaginated(ROOT_GAME_ENDPOINT, null, Game.class);
	return arr.getData();
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
	var pagination=new Pagination();
	while(pagination.hasMore())
	{
		var result =  client.getPaginated(ROOT_GAME_ENDPOINT+"/"+id+"/expansions?offset="+ret.size()+"&limit="+NexusConfig.LIMIT_LIST_RESULTS, null, Expansion.class);
		ret.addAll(result.getData());
		pagination = result.getPagination();
	}
	return ret;
    }
    
    public AbstractProduct getProductById(Integer id) throws IOException
    {
	return client.get(ROOT_PRODUCT_ENDPOINT+"/"+id, null, AbstractProduct.class);
    }
    
    
    public List<AbstractProduct> searchProduct(SearchProductRequest req) throws IOException
    {
	var ret = new ArrayList<AbstractProduct>();
	var pagination=new Pagination();
	while(pagination.hasMore())
	{
	    req.setOffset(ret.size());
	    var result = client.postPaginated(ROOT_PRODUCT_ENDPOINT+"/search",req,null,AbstractProduct.class);
	    ret.addAll(result.getData());
	    pagination=result.getPagination();
	}
	return ret;
    }
    
    
    
    
    
}
