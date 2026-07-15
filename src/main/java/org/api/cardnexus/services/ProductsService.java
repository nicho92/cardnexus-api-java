package org.api.cardnexus.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.Pagination;
import org.api.cardnexus.model.enums.EnumFeedKey;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.requests.SearchProductRequest;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ProductsService extends AbstractNexusService{

    
    Cache<Integer, AbstractProduct> productsCache ;
    Cache<Integer, Expansion> expansionCache;
    
    public ProductsService() {
	super();
		productsCache = Caffeine.newBuilder().build();
		expansionCache = Caffeine.newBuilder().build();
		
    }
    
    public List<Game> listGames() throws IOException
    {
    	return client.getPaginated(ROOT_GAME_ENDPOINT, null, Game.class).getData();
    }
    
    public Game getGameById(String id) throws IOException
    {
    	return client.get(ROOT_GAME_ENDPOINT+id, null, Game.class);
    }
    
    public Expansion getExpansionById(Integer id) throws IOException
    {
    	return expansionCache.get(id, _->{
    		try {
    			logger.debug("Expansion {} is not in cache. getting it",id);
				return client.get(ROOT_EXPANSION_ENDPOINT+"/"+id, null, Expansion.class);
			} catch (IOException e) {
				logger.error(e);
				return null;
			}
    	});
    	
    }

    public List<Expansion> listExpansion(Game game) throws IOException
    {
    	return listExpansion(game.getId());
    }
    
    public List<Expansion> listExpansion(String gameid) throws IOException
    {
		var ret = new ArrayList<Expansion>();
		var pagination=new Pagination();
		while(pagination.hasMore())
		{
			var result =  client.getPaginated(ROOT_GAME_ENDPOINT+"/"+gameid+"/expansions?offset="+ret.size()+"&limit="+NexusConfig.LIMIT_LIST_RESULTS, null, Expansion.class);
			ret.addAll(result.getData());
			
			result.getData().forEach(ex->expansionCache.put(ex.getId(), ex));
			
			
			pagination = result.getPagination();
		}
		return ret;
    }
    
    public AbstractProduct getProductById(Integer id)
    {
		return productsCache.get(id, _->{
		    try {
				logger.debug("{} is not in cache. getting it",id);
				return client.get(ROOT_PRODUCT_ENDPOINT+"/"+id, null, AbstractProduct.class);
		    } catch (IOException e) {
				logger.error(e);
				return null;
		    }    
		});	
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
		
		ret.forEach(p->productsCache.put(p.getId(), p));
		
		if(req.isStrictTerms() && req.getName()!=null)
		    return ret.stream().filter(p->p.getName().equalsIgnoreCase(req.getName())).toList();
		
		return ret;
    }
    
    public Map<Integer,Integer> resolveProductsId(EnumMarketPlace market, List<Integer> ids) throws IOException
    {
		var obj = new JsonObject();
		    obj.addProperty("marketplace", market.name());
		
		      var idsarr = new JsonArray();
		      	ids.forEach(idsarr::add);
		      
		    obj.add("ids", idsarr);
		      
		var arr = client.post(ROOT_PRODUCT_ENDPOINT+"/resolve", obj, null, JsonObject.class).get("results").getAsJsonArray();
		
		var map = new HashMap<Integer,Integer>();
		
		for(var el : arr)
		{
		    map.put(el.getAsJsonObject().get("externalId").getAsInt(), el.getAsJsonObject().get("product").getAsJsonObject().get("id").getAsInt());
		}
			
		return map;
    }
    
    
    public void cachingProducts(String gameId, boolean forceDownload) throws IOException
    {
		var serv =new FeedsService();
		
		var f = new File(NexusConfig.getFileDirectory(), "catalog.ndjson");
		
		if(forceDownload || !f.exists())
		{
			logger.warn("force download = {} and exists={}",forceDownload,f.exists());
			f = serv.download(gameId, EnumFeedKey.catalog);
		}
		
		logger.info("begin caching");
		Files.readAllLines(f.toPath()).forEach(s->{
		    var obj = client.fromJson(s, AbstractProduct.class);
		    productsCache.put(obj.getId(), obj);
		});
		logger.info("Cached {} products for {}", productsCache.estimatedSize(), gameId );
		
	}
    
    
    
}
