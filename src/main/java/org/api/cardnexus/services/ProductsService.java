package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.configuration.NexusConstants;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.services.interfaces.AbstractNexusService;

import com.google.gson.JsonObject;

public class ProductsService extends AbstractNexusService{

    
    private static final String ROOT_ENDPOINT="/games";
    
    
    
    public List<Game> listGames() throws IOException
    {
	var arr = client.get(ROOT_ENDPOINT, null, JsonObject.class).get("data").getAsJsonArray();
	return client.toList(arr, Game.class);
    }
    
    public Game getGameById(String id) throws IOException
    {
	return client.get(ROOT_ENDPOINT+id, null, Game.class);
    }
    

    public List<Expansion> listExpansion(Game game) throws IOException
    {
	return listExpansion(game.getId());
    }
    
    
    public List<Expansion> listExpansion(String id) throws IOException
    {
	var ret = new ArrayList<Expansion>();
	
	var result =  client.getPaginated(ROOT_ENDPOINT+"/"+id+"/expansions?offset="+ret.size()+"&limit="+NexusConstants.LIMIT_LIST_RESULTS, null, Expansion.class);
	
	ret.addAll(result.getData());
	
	
	while(result.getPagination().hasMore())
	{
	    result = client.getPaginated(ROOT_ENDPOINT+"/"+id+"/expansions?offset="+ret.size()+"&limit="+NexusConstants.LIMIT_LIST_RESULTS, null, Expansion.class);
	    ret.addAll(result.getData());
	}
	
	return ret;
    }
    
    
}
