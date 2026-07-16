package org.api.cardnexus.model;

import java.util.Map;

public record User (String id,String username, String avatarUrl,  String country,String type,Map<String,Double> rating)
{
    
}
