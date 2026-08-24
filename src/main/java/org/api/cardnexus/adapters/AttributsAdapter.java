package org.api.cardnexus.adapters;

import java.lang.reflect.Type;

import org.api.cardnexus.model.AbstractCardAttributs;
import org.api.cardnexus.model.attributs.MTGAttributs;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class AttributsAdapter implements JsonDeserializer<AbstractCardAttributs>{

    @Override
    public AbstractCardAttributs deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
	
	//TODO manage attributs
	
	
	return context.deserialize(json,MTGAttributs.class);
    }

}
