package org.api.cardnexus.services;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.EnumMap;
import java.util.Map;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.Feed;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.enums.EnumFeedKey;
import org.api.cardnexus.tools.FileTools;

import com.google.gson.JsonObject;

public class FeedsService extends AbstractNexusService{
   
    public Map<EnumFeedKey, Feed> getGameFeeds(String gameId) throws IOException
    {
	var obj = client.get(ROOT_FEED_ENDPOINT+"/"+gameId, null, JsonObject.class);
	var m = new EnumMap<EnumFeedKey, Feed>(EnumFeedKey.class);
	obj.keySet().forEach(k->{
	    var f = client.fromJson(obj.get(k).toString(), Feed.class);
	    m.put(EnumFeedKey.valueOf(k), f);
	});
	return m;
    }
    
    public Feed getFeed(Game game, EnumFeedKey f) throws IOException
    {
	return getFeed(game.getId(), f);
    }
    
    public Feed getFeed(String gameId, EnumFeedKey f) throws IOException
    {
	return client.get(ROOT_FEED_ENDPOINT+"/"+gameId+"/"+f.name(), null, Feed.class);
    }
    
    public File download(String gameId,EnumFeedKey k) throws IOException
    {
	var feed = getFeed(gameId, k);
	var zipFile = FileTools.download(URI.create(feed.getUrl()).toURL(),k);
	var dFile = new File(NexusConfig.getFileDirectory(), k.name()+".json");
	FileTools.unZipIt(zipFile, dFile);
	return dFile;
	
    }
    
    
}
