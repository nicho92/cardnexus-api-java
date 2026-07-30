package org.api.cardnexus.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.enums.EnumFeedKey;
import org.api.cardnexus.services.FeedsService;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CachingService {

    protected Logger logger = LogManager.getLogger(getClass());
    private static CachingService instance;
    private Cache<Integer, AbstractProduct> productsCache ;
    private Cache<Integer, Expansion> expansionCache;
    private Cache<String, Game> gamesCache;
    
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
 

    
    public void cachingProducts(String gameId, boolean forceDownload) throws IOException
    {
		var serv =new FeedsService();
		var gson = new JsonService();
		
		var f = new File(NexusConfig.DIRECTORY_FEED, "catalog.ndjson");
		
		if(forceDownload || !f.exists())
		{
			logger.warn("force= {} or exists={}",forceDownload,f.exists());
			f = serv.download(gameId, EnumFeedKey.catalog);
		}
		
		logger.info("begin caching");
		Files.readAllLines(f.toPath()).forEach(s->{
		    AbstractProduct obj = gson.fromJson(s, AbstractProduct.class);
		    CachingService.inst().getProductsCache().put(obj.getId(), obj);
		});
		logger.info("Cached {} products for {}", CachingService.inst().getProductsCache().estimatedSize(), gameId );
   }
    
    
    
}
