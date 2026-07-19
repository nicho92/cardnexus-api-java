package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record InventoryLine (String id,String customId,String comment,int productId,String game,EnumFinishes finish,EnumCondition condition,String language,Integer quantity,boolean forSale,Date updatedAt,Map<String,Amount> listings,Graded graded,List<String> tags)
{
    
    public Amount price() {
	if(listings.isEmpty())
		return new Amount(0.0, null);
	
	return listings.get("price");
    }
    
}
