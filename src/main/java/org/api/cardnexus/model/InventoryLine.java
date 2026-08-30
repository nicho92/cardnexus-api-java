package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record InventoryLine (String id,String customId,String comment,int productId,String game,EnumFinishes finish,EnumCondition condition,String language,Integer quantity,boolean forSale,String location, Date updatedAt,Map<String,Amount> listings,Grading graded,String notes,List<String> tags)
{
    public Amount price() {
	if(listings==null || listings.isEmpty())
		return null;
	
	return listings.get("price");
    }
    
}
