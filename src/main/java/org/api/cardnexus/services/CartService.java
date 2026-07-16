package org.api.cardnexus.services;

import java.io.IOException;

import org.api.cardnexus.model.Run;
import org.api.cardnexus.model.requests.CardOptimizationRequest;

import com.google.gson.JsonObject;

public class CartService extends AbstractNexusService {

    
    public String runOptimizationQuery(CardOptimizationRequest req) throws IOException
    {
	return client.post(ROOT_OPTIMIZER_ENDPOINT+"/runs", req, JsonObject.class).get("id").getAsString();
    }
    
    
    public Run getRunById(String id) throws IOException
    {
	return client.get(ROOT_OPTIMIZER_ENDPOINT+"/runs/"+id, Run.class);
    }
        
}
