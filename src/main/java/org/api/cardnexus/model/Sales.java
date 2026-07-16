package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record Sales (Date soldAt,String region,int quantity,EnumFinishes finish,String language,EnumCondition condition,Amount price,Amount priceEur)
{
    
}
