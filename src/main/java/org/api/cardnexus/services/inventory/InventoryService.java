package org.api.cardnexus.services.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.InventoryLine;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.services.AbstractNexusService;

import com.google.gson.JsonObject;

public class InventoryService extends AbstractNexusService{

    
    public List<InventoryLine> getInventoryLines(InventoryRequest req) throws IOException
    {
	var result = client.getPaginated(ROOT_INVENTORY_ENDPOINT+"?"+req.toQueryString(), null, InventoryLine.class);
	var ret = new ArrayList<InventoryLine>();
	var pagination=result.getPagination();
	
	while(pagination.getNextCursor()!=null)
	{
		result = client.getPaginated(ROOT_INVENTORY_ENDPOINT+"?cursor="+pagination.getNextCursor()+"&"+req.toQueryString(), null, InventoryLine.class);
		ret.addAll(result.getData());
		pagination = result.getPagination();
	}
	return ret;
	
    }
    
    public InventoryLine getInventoryLine(String inventoryId) throws IOException
    {
	return client.get(ROOT_INVENTORY_ENDPOINT+"/"+inventoryId, null, InventoryLine.class);
    }
    
    public boolean deleteInventoryLine(String inventoryId) throws IOException
    {
	return client.delete(ROOT_INVENTORY_ENDPOINT+"/"+inventoryId, null, null,JsonObject.class).get("deleted").getAsBoolean();
    }
    
}
