package org.api.cardnexus.model;

public record Pagination (Integer offset,Integer limit, Integer total, boolean hasMore, String nextCursor)
{
    
}
