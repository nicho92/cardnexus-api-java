package org.api.cardnexus.services;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import org.api.cardnexus.configuration.AbstractNexusService;
import org.api.cardnexus.model.Feed;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.enums.EnumFeedKey;

import com.google.gson.JsonObject;

public class FeedsService extends AbstractNexusService{

    private static final String ROOT_FEED_ENDPOINT="/feeds";
    
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
    
    
    
}
