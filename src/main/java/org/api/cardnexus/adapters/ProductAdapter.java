package org.api.cardnexus.adapters;

import java.lang.reflect.Type;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.enums.EnumProductType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ProductAdapter implements JsonDeserializer<AbstractProduct>{
    	protected Logger logger = LogManager.getLogger(getClass());
    	@Override
	public AbstractProduct deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) throws JsonParseException {
		try {
		    	var typeProduct=elem.getAsJsonObject().get("productType");
		    	return context.deserialize(elem,typeForName(EnumProductType.valueOf(typeProduct.getAsString())));
		} 
		catch (Exception ex) 
		{
		    	logger.error(ex);
		    	
		    	logger.error(elem);
		    	
			throw new JsonParseException(ex.getMessage());
		}
	}

	private Type typeForName(final EnumProductType t) {
		if (t.equals(EnumProductType.card))
			return CardProduct.class;

		return SealedProduct.class;

	}

}
