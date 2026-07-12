package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.InventoryLine;
import org.api.cardnexus.model.Location;
import org.api.cardnexus.model.Tag;
import org.api.cardnexus.model.requests.InventoryRequest;

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
    
    public List<Tag> listTags() throws IOException
    {
	return client.getPaginated(ROOT_INVENTORY_ENDPOINT+"/tags", null,Tag.class).getData();
    }
    
    public Tag createTag(Tag t) throws IOException
    {
	return client.post(ROOT_INVENTORY_ENDPOINT+"/tags", t, null, Tag.class);
    }
    
    public boolean deleteTag(String tagName) throws IOException
    {
	try{
	    client.post(ROOT_INVENTORY_ENDPOINT+"/tags/"+tagName, null, null, Object.class);
	    return true;
	}
	catch(Exception _)
	{
	    return false;
	}
	
    }
    
    public List<Location> listLocations() throws IOException
    {
	return client.getPaginated(ROOT_INVENTORY_ENDPOINT+"/locations", null,Location.class).getData();
    }
    
    public Location createLocation(Location t) throws IOException
    {
	return client.post(ROOT_INVENTORY_ENDPOINT+"/locations", t, null, Location.class);
    }
    
    public boolean deleteLocation(String locName) throws IOException
    {
	try{
	    client.post(ROOT_INVENTORY_ENDPOINT+"/locations/"+locName, null, null, Object.class);
	    return true;
	}
	catch(Exception _)
	{
	    return false;
	}
	
    }
    
    public List<InventoryLine> listInventoryInMarketPlace(InventoryRequest req) throws IOException
    {
	var result = client.getPaginated(ROOT_LISTING_ENDPOINT+"?"+req.toQueryString(), null, InventoryLine.class);
	var ret = new ArrayList<InventoryLine>();
	var pagination=result.getPagination();
	
	while(pagination.getNextCursor()!=null)
	{
		result = client.getPaginated(ROOT_LISTING_ENDPOINT+"?cursor="+pagination.getNextCursor()+"&"+req.toQueryString(), null, InventoryLine.class);
		ret.addAll(result.getData());
		pagination = result.getPagination();
	}
	return ret;
    }
    
    
    
    
    
}
