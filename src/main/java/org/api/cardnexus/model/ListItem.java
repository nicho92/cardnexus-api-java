package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record ListItem (String id,Integer productId,String name, String nameSlug,String expansion,EnumFinishes finish,String language,EnumCondition minCondition,Integer quantity,Integer quantityFulfilled,Double wantPrice,Double sellPrice)
{
        
    
}
