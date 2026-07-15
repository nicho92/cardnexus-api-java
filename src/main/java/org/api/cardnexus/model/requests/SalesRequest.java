package org.api.cardnexus.model.requests;

import org.api.cardnexus.model.enums.EnumOrderStatus;

public class SalesRequest extends AbstractGetRequest{

  
    private EnumOrderStatus status;
    
    public EnumOrderStatus getStatus() {
        return status;
    }
    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }
    
    
    
}

