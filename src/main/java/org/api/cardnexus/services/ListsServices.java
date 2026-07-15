package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.NexusList;
import org.api.cardnexus.model.NexusListFull;
import org.api.cardnexus.model.Pagination;
import org.api.cardnexus.model.requests.ListCreationRequest;
import org.api.cardnexus.model.requests.ListItemRequest;

import com.google.gson.JsonObject;

public class ListsServices extends AbstractNexusService {

    public NexusListFull getNexusLists(String listId) throws IOException
    {
	return   client.get(ROOT_LISTS_ENDPOINT+"/"+listId, null, NexusListFull.class);
    }
    
    public List<NexusList> listNexusLists() throws IOException
    {
	var ret = new ArrayList<NexusList>();
	var pagination=new Pagination();
	while(pagination.hasMore())
	{
		var result =  client.getPaginated(ROOT_LISTS_ENDPOINT+"?offset="+ret.size()+"&limit="+NexusConfig.LIMIT_LIST_RESULTS, null, NexusList.class);
		ret.addAll(result.getData());
		pagination = result.getPagination();
	}
	return ret;
    }
    
    public NexusList createList(ListCreationRequest req) throws IOException
    {
	return client.post(ROOT_LISTS_ENDPOINT, req, null, NexusList.class);
    }
    
    public NexusListFull updateList(String listId,ListCreationRequest req) throws IOException
    {
	return client.patch(ROOT_LISTS_ENDPOINT+"/"+listId, req, null, NexusListFull.class);
    }
    
    public boolean deleteList(String listId) throws IOException
    {
	return client.delete(ROOT_LISTS_ENDPOINT+"/"+listId, null, null, JsonObject.class).get("deleted").getAsBoolean();
    }
    
    public NexusListFull updateListItems(String listId, ListItemRequest req)  throws IOException
    {
	return client.post(ROOT_LISTS_ENDPOINT+"/"+listId+"/items", req, null, NexusListFull.class);
    }
    
    public boolean removeItem(String listId, String itemId)  throws IOException
    {
	return client.post(ROOT_LISTS_ENDPOINT+"/"+listId+"/items/"+itemId, null, null, JsonObject.class).get("deleted").getAsBoolean();
    }
    
    
}
