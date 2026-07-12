package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.configuration.NexusConstants;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.model.PaginateResult;
import org.api.cardnexus.services.interfaces.AbstractNexusService;

import com.google.gson.JsonObject;

public class ProductsService extends AbstractNexusService{

    
    public List<Game> listGames() throws IOException
    {
	var arr = client.get("/games", null, JsonObject.class).get("data").getAsJsonArray();
	return arr.asList().stream().map(e->client.fromJson(e.toString(),Game.class)).toList();
    }
    
    public Game getGameById(String id) throws IOException
    {
	return client.get("/games/"+id, null, Game.class);
    }
    

    public List<Expansion> listExpansion(Game game) throws IOException
    {
	return listExpansion(game.getId());
    }
    
    
    public List<Expansion> listExpansion(String id) throws IOException
    {
	var ret = new ArrayList<Expansion>();
	
	PaginateResult<Expansion> results = client.get("/games/"+id+"/expansions?offset="+ret.size()+"&limit="+NexusConstants.LIMIT_LIST_RESULTS, null, PaginateResult.class);
	
	System.out.println();
	
	
	
	return ret;
	
	
    }
    
    
}
