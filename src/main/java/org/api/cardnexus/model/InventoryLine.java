package org.api.cardnexus.model;

import java.util.Date;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record InventoryLine (String id,String customId,String comment,Integer productId,String game,EnumFinishes finish,EnumCondition condition,String language,Integer quantity,boolean forSale,Date updatedAt,Map<String,Amount> listings,Graded graded)
{

    
}
