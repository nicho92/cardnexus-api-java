package org.api.cardnexus.model;

import java.util.Date;

public record Game (String id,String name,Date releaseDate,Integer cardCount,Integer sealedProductCount,String logoUrl)
{
    
    @Override
    public String toString() {
        return name();
    }
     
}
