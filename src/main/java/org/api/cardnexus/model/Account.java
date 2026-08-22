package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;

import org.api.cardnexus.model.enums.EnumScopes;

public record Account (String id,String username,String email,String imageUrl,Date createdAt,Seller seller, List<EnumScopes> scopes)
{
 
}
