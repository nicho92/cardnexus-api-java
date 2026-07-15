package org.api.cardnexus.services;

import java.io.IOException;

import org.api.cardnexus.model.Run;
import org.api.cardnexus.model.requests.CardOptimizationRequest;

import com.google.gson.JsonObject;

public class CartService extends AbstractNexusService {

    
    public String runs(CardOptimizationRequest req) throws IOException
    {
	return client.post(ROOT_OPTIMIZER_ENDPOINT+"/runs", req, null, JsonObject.class).get("id").getAsString();
    }
    
    
    public Run runs(String id) throws IOException
    {
	return client.get(ROOT_OPTIMIZER_ENDPOINT+"/runs/"+id, null, Run.class);
    }
    
    
    
}
