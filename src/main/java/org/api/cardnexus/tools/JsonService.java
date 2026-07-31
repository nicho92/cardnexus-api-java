package org.api.cardnexus.tools;

import java.lang.reflect.Type;
import java.util.List;

import org.api.cardnexus.adapters.ProductAdapter;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class JsonService {

    
    private final Gson gson;
    
    public JsonService() {
        var builder = new GsonBuilder().registerTypeAdapter(AbstractProduct.class, new ProductAdapter());
        
        if(NexusConfig.isGsonPrettyPrint())
            builder.setPrettyPrinting();
        
        
        this.gson=builder.create();
    }

    public JsonElement toJsonTree(Object o)
    {
	return gson.toJsonTree(o);
    }
    
    
    public String toJson(Object body) {
	return gson.toJson(body);
    }

    public <T> List<T> fromJson(JsonArray json, Type listType) {
	return gson.fromJson(json, listType);
    }

    public <T> T fromJson(String json, Type responseType) {
	return gson.fromJson(json, responseType);
    }
}
