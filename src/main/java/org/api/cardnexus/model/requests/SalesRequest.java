package org.api.cardnexus.model.requests;

import org.api.cardnexus.model.enums.EnumOrderStatus;

public class SalesRequest extends AbstractGetRequest{

    private Integer limit=100;
    private EnumOrderStatus status;
    
    
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public EnumOrderStatus getStatus() {
        return status;
    }
    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }
    
    
    
}

