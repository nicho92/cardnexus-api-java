package org.api.cardnexus.model;

import java.util.List;

public record PaginateResult<T> (List<T> data, Pagination pagination)
{
    
}
