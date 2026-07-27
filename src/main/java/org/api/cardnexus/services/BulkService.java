package org.api.cardnexus.services;

import java.io.IOException;

import org.api.cardnexus.model.Job;
import org.api.cardnexus.model.enums.EnumBulkFormat;
import org.api.cardnexus.model.requests.SearchInventoryRequest;
import org.api.cardnexus.tools.JsonService;

import com.google.gson.JsonObject;

public class BulkService extends AbstractNexusService {

    
    private JsonService s;

    public BulkService() {
	s = new JsonService();
    }
    

    public Job getJob(String id) throws IOException
    {
	return  s.fromJson(client.get(ROOT_INVENTORY_ENDPOINT+"/bulk/jobs/"+id, JsonObject.class).get("job").getAsJsonObject().toString(),Job.class);
    }
    
    public Job bulkExport(SearchInventoryRequest req, EnumBulkFormat format) throws IOException
    {
	var obj = new JsonObject();
	obj.addProperty("format", format.name());
	obj.add("filters", s.toJsonTree(req));
	
	return  s.fromJson(client.post(ROOT_INVENTORY_ENDPOINT+"/bulk/export", obj,  JsonObject.class).get("job").getAsJsonObject().toString(),Job.class);
    }
    
    
}
