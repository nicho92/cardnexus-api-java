package org.api.cardnexus.tools;

import java.lang.reflect.Type;

import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.enums.EnumProductType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ProductAdapter implements JsonDeserializer<AbstractProduct>{

    @Override
	public AbstractProduct deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) throws JsonParseException {
		try {
			return context.deserialize(elem,typeForName(EnumProductType.valueOf(elem.getAsJsonObject().get("productType").getAsString())));
		} catch (Exception ex) {
			return context.deserialize(elem, typeForName(EnumProductType.sealed));
		}
	}

	private Type typeForName(final EnumProductType t) {
		if (t.equals(EnumProductType.card))
			return CardProduct.class;

		return SealedProduct.class;

	}

}
