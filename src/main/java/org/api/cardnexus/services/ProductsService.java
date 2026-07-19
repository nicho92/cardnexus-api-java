package org.api.cardnexus.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.MarketList;
import org.api.cardnexus.model.Pagination;
import org.api.cardnexus.model.enums.EnumFeedKey;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.enums.EnumSearchMod;
import org.api.cardnexus.model.requests.MarketListRequest;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.tools.CachingService;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ProductsService extends AbstractNexusService{

    
    Cache<Integer, AbstractProduct> productsCache ;
    Cache<Integer, Expansion> expansionCache;
    Cache<String, Game> gamesCache;
    
    public ProductsService() {
	super();
		productsCache = CachingService.createCache();
		expansionCache = CachingService.createCache();
		gamesCache = CachingService.createCache();
		
    }
    
    public List<Game> listGames() throws IOException
    {
    	var res =  client.getPaginated(ROOT_GAME_ENDPOINT, Game.class).data();
    	
    	res.forEach(g->gamesCache.put(g.id(),g));
    	
    	return res;
    	
    }
    
    public Game getGameById(String id)
    {
	
	return gamesCache.get(id, _->{
	    try {
		return client.get(ROOT_GAME_ENDPOINT+id, Game.class);
	    } catch (IOException e) {
		logger.error(e);
		return null;
	    }
	});
	
	
    	
    }
    
    public Expansion getExpansionById(Integer id) 
    {
    	return expansionCache.get(id, _->{
    		try {
    			logger.debug("Expansion {} is not in cache. getting it",id);
				return client.get(ROOT_EXPANSION_ENDPOINT+"/"+id, Expansion.class);
			} catch (IOException e) {
				logger.error(e);
				return null;
			}
    	});
    	
    }

    public List<Expansion> listExpansion(Game game) throws IOException
    {
    	return listExpansion(game.id());
    }
    
    public List<Expansion> listExpansion(String gameid) throws IOException
    {
		var ret = new ArrayList<Expansion>();
		var pagination=new Pagination(null,null,null,true,null);
		while(pagination.hasMore())
		{
			var result =  client.getPaginated(ROOT_GAME_ENDPOINT+"/"+gameid+"/expansions?offset="+ret.size()+"&limit="+NexusConfig.LIMIT_LIST_RESULTS, Expansion.class);
			ret.addAll(result.data());
			
			result.data().forEach(ex->expansionCache.put(ex.id(), ex));
			
			
			pagination = result.pagination();
		}
		return ret;
    }
    
    public AbstractProduct getProductById(Integer id)
    {
		return productsCache.get(id, _->{
		    try {
				logger.debug("{} is not in cache. getting it",id);
				return client.get(ROOT_PRODUCT_ENDPOINT+"/"+id, AbstractProduct.class);
		    } catch (IOException e) {
				logger.error(e);
				return null;
		    }    
		});	
    }
     
    public List<AbstractProduct> searchProduct(SearchProductRequest req) throws IOException
    {
		var ret = new ArrayList<AbstractProduct>();
		var pagination=new Pagination(null,null,null,true,null);
		while(pagination.hasMore())
		{
		    req.setOffset(ret.size());
		    var result = client.postPaginated(ROOT_PRODUCT_ENDPOINT+"/search",req,AbstractProduct.class);
		    ret.addAll(result.data());
		    
		    pagination=result.pagination();
		}
		
		ret.forEach(p->{
		    if(p.getExpansion()==null)
			p.setExpansion(getExpansionById(p.getExpansionId()));
		});
		ret.forEach(p->productsCache.put(p.getId(), p));
		
		if(req.getNameSearchMod()==EnumSearchMod.STRICT && req.getName()!=null)
		{
		    return ret.stream().filter(p->p.getName().equalsIgnoreCase(req.getName())).toList();
		}
		else if(req.getNameSearchMod()==EnumSearchMod.CONTAINS && req.getName()!=null)
		{
		    List<String> words = Arrays.asList(StringUtils.split(req.getName()));
		    return ret.stream().filter(o -> words.stream().allMatch(word ->Strings.CI.contains(o.getName(), word))).toList();
		}
		    
 
		    
		return ret;
    }
    
    public Map<Integer,Integer> resolveProductsId(EnumMarketPlace market, List<Integer> ids) throws IOException
    {
		var obj = new JsonObject();
		     obj.addProperty("marketplace", market.name());
		
		      var idsarr = new JsonArray();
		      	ids.forEach(idsarr::add);
		      
		    obj.add("ids", idsarr);
		      
		var arr = client.post(ROOT_PRODUCT_ENDPOINT+"/resolve", obj, JsonObject.class).get("results").getAsJsonArray();
		
		var map = new HashMap<Integer,Integer>();
		
		for(var el : arr)
		    map.put(el.getAsJsonObject().get("externalId").getAsInt(), el.getAsJsonObject().get("product").getAsJsonObject().get("id").getAsInt());
			
		return map;
    }
    
    
    public List<MarketList> listMarketListing(MarketListRequest req) throws IOException
    {
	var result = client.getPaginated(ROOT_PRODUCT_ENDPOINT+"/"+req.getProductId()+"/listings?"+req.toQueryString(),  MarketList.class);
	var ret = new ArrayList<MarketList>();
	var pagination=result.pagination();
	
	ret.addAll(result.data());
	while(pagination.nextCursor()!=null)
	{
		result = client.getPaginated(ROOT_INVENTORY_ENDPOINT+"/"+req.getProductId()+"/listings"+"?cursor="+pagination.nextCursor()+"&"+req.toQueryString(),  MarketList.class);
		ret.addAll(result.data());
		pagination = result.pagination();
	}
	return ret;
	
    }
    
    
    
    public void cachingProducts(String gameId, boolean forceDownload) throws IOException
    {
		var serv =new FeedsService();
		
		var f = new File(NexusConfig.DIRECTORY_FEED, "catalog.ndjson");
		
		if(forceDownload || !f.exists())
		{
			logger.warn("force= {} or exists={}",forceDownload,f.exists());
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
