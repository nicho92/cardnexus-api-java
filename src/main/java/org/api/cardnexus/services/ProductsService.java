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
import org.api.cardnexus.model.MarketList;
import org.api.cardnexus.model.Pagination;
import org.api.cardnexus.model.enums.EnumFeedKey;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.requests.MarketListRequest;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.tools.CachingService;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ProductsService extends AbstractNexusService{

    
    Cache<Integer, AbstractProduct> productsCache ;
    Cache<Integer, Expansion> expansionCache;
    
    public ProductsService() {
	super();
		productsCache = CachingService.createCache();
		expansionCache = CachingService.createCache();
    }
    
    
    /**
     * Returns every game CardNexus tracks. Use the id of each entry to address the game in other catalogue endpoints.
     * @return List<Game>
     * @throws IOException
     */
    public List<Game> listGames() throws IOException
    {
    	return client.getPaginated(ROOT_GAME_ENDPOINT, Game.class).data();
    }
    
    /***
     * Returns a single game by its identifier.
     * @param id
     * @return Game
     * @throws IOException
     */
    
    public Game getGameById(String id) throws IOException
    {
    	return client.get(ROOT_GAME_ENDPOINT+id, Game.class);
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

    /**
     * Returns every expansion (set) of a game, paginated. Newest expansions first by release date.
     * @param game
     * @return List<Expansion>
     * @throws IOException
     */
    
    public List<Expansion> listExpansion(Game game) throws IOException
    {
    	return listExpansion(game.id());
    }
    
    /**
     * Returns every expansion (set) of a game, paginated. Newest expansions first by release date.
     * @param gameid
     * @return List<Expansion>
     * @throws IOException
     */    
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
    
    /**
     * Returns a single product by its identifier, including cross-platform IDs that map this product to its Cardmarket and TCGplayer equivalents.
     * The response is a discriminated union by productType (card or sealed).
     * @param id
     * @return AbstractProduct
     */
    
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
     
    /**
     * Searches the catalogue. Returns a paginated list of products matching the body.
     * Use this to find a card by name, browse an expansion, or filter on game-specific attributes like rarity or color. To replicate the full catalogue, use GET /v1/feeds/products instead — the feed is regenerated whenever a game's catalogue updates (typically minutes after a change).
     * This endpoint has its own rate-limit bucket (catalogue-search); paginating through tens of thousands of results will hit it before your account-level limit. The daily feeds are the right tool for bulk catalogue replication.
     * @param req
     * @return List<AbstractProduct>
     * @throws IOException
     */
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
		
		ret.forEach(p->p.setExpansion(getExpansionById(p.getExpansionId())));
		ret.forEach(p->productsCache.put(p.getId(), p));
		
		if(req.isStrictTerms() && req.getName()!=null)
		    return ret.stream().filter(p->p.getName().equalsIgnoreCase(req.getName())).toList();
		
		return ret;
    }
    
    /**
     * Maps Cardmarket or TCGplayer product ids to CardNexus products, up to 200 ids per call. Each id resolves to at most one product, including the finish the id maps to — a marketplace product id is finish-specific, so a card's Foil and Standard printings carry different ids. Ids with no match come back with product: null, so a single call gives you a complete mapping table, misses included.
     * Pair this with the bulk import: resolve your Cardmarket idProduct (or TCGplayer product id) column to CardNexus product ids, then send those as the productId on each POST /v1/inventory/bulk/import row.
     * @param market
     * @param ids
     * @return Map<Integer,Integer>
     * @throws IOException
     */
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
