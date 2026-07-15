package org.api.cardnexus.model;

import java.util.Date;

public record Account (String username,String email,String imageUrl,Date createdAt,Seller seller)
{
    
    @Override
    public String toString() {
          return username();
    }
}
