package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;

public record Expansion (Integer id,String gameId,String name,String code,Date releaseDate,Integer cardCount,Integer sealedProductCount,List<String> languages,String logoUrl,String symbolUrl)
{
    
    @Override
    public String toString() {
          return name();
    }
}