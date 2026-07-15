package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record OrderItem(Integer productId,String productName,String imageUrl,EnumCondition condition,String language,EnumFinishes finish,Integer quantity,Amount unitPrice,Amount lineTotal) {
    
}