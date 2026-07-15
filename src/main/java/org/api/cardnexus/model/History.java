package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;

public record History (Date date, EnumMarketPlace marketplace,EnumFinishes finish,Double low,Double mid,Double high,Double marketValue)
{
        
    
}
