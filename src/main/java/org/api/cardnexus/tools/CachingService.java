package org.api.cardnexus.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.ProductPriceMarket;
import org.api.cardnexus.model.enums.EnumFeedKey;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.services.FeedsService;
import org.api.cardnexus.services.ProductsService;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class CachingService {

    protected Logger logger = LogManager.getLogger(getClass());
    private static CachingService instance;
    private Cache<Integer, AbstractProduct> productsCache ;
    private Cache<Integer, Expansion> expansionCache;
    private Cache<String, Game> gamesCache;
    private Cache<Integer, Map<EnumFinishes, ProductPriceMarket>> pricesCache;
    
    
    public static <K, V> Cache<K, V> createCache()
    {
	return Caffeine.newBuilder().build();
    }
    
    public static CachingService inst()
    {
	if(instance==null)
	    instance = new CachingService();
	
	return instance;
    }
    
    
    private CachingService()
    {
	productsCache = createCache();
	expansionCache=createCache();
	gamesCache = createCache();
	pricesCache = createCache();
    }
    
    
    public Cache<Integer, Expansion> getExpansionCache() {
	return expansionCache;
    }
    public Cache<String, Game> getGamesCache() {
	return gamesCache;
    }
    
    public Cache<Integer, AbstractProduct> getProductsCache() {
	return productsCache;
    }
    
    public Cache<Integer, Map<EnumFinishes, ProductPriceMarket>> getPricesCache() {
	return pricesCache;
    }

    
    public void cachingProducts(String gameId) throws IOException
    {
		var serv =new FeedsService();
		var gson = new JsonService();
		var pService = new ProductsService();
		
		var fProdudct = new File(NexusConfig.getTempDirectory(), "catalog.ndjson");
		var fPrices = new File(NexusConfig.getTempDirectory(), "prices.ndjson");
		
		
		pService.listExpansion(NexusConfig.getDefaultGameValue()); 
			
		    
		if(!fProdudct.exists() || FileTools.daysBetween(fProdudct)>NexusConfig.getFeedRententionDurationDays())
		{
			logger.warn("{} retention>{} days or exists={}",fProdudct,NexusConfig.getFeedRententionDurationDays(),fProdudct.exists());
			fProdudct = serv.download(gameId, EnumFeedKey.catalog);
		}
		
		if(!fPrices.exists() || FileTools.daysBetween(fPrices)>NexusConfig.getFeedRententionDurationDays())
		{
			logger.warn("{} retention>{} days or exists={}",fPrices,NexusConfig.getFeedRententionDurationDays(),fPrices.exists());
			fPrices = serv.download(gameId, EnumFeedKey.prices);
		}
		
		logger.info("begin caching Prices");
		Files.readAllLines(fPrices.toPath()).forEach(s->{
		    JsonObject obj = gson.fromJson(s, JsonObject.class);
		    CachingService.inst().getPricesCache().put(obj.get("productId").getAsInt(), gson.fromJson(obj.get("pricesByFinish").toString(), new TypeToken<Map<EnumFinishes, ProductPriceMarket>>() { }.getType()));
		});
		logger.info("Cached {} prices for {}", CachingService.inst().getPricesCache().estimatedSize(), gameId );
		
		
		
		logger.info("begin caching Product");
		Files.readAllLines(fProdudct.toPath()).forEach(s->{
		    AbstractProduct obj = gson.fromJson(s, AbstractProduct.class);
		    obj.setExpansion(pService.getExpansionById(obj.getExpansionId()));
		    obj.setPrices(getPricesCache().getIfPresent(obj.getId()));
		    CachingService.inst().getProductsCache().put(obj.getId(), obj);
		});
		logger.info("Cached {} products for {}", CachingService.inst().getProductsCache().estimatedSize(), gameId );
		
		
		
   }
    
    
    
}
