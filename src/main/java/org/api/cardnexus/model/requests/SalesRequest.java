package org.api.cardnexus.model.requests;

import java.time.LocalDate;

import org.api.cardnexus.model.enums.EnumOrderStatus;

public class SalesRequest extends AbstractGetRequest{

  
    private EnumOrderStatus status;
    private LocalDate placedFrom;
    private LocalDate placedTo;
    
    
    private SalesRequest()
    {
		
    }
    
    public static SalesRequest create()
    {
	return new SalesRequest();
    }
    
    
    public EnumOrderStatus getStatus() {
        return status;
    }
    public SalesRequest setStatus(EnumOrderStatus status) {
        this.status = status;
        return this;
    }

    public LocalDate getPlacedFrom() {
        return placedFrom;
    }

    public SalesRequest setPlacedFrom(LocalDate placedFrom) {
        this.placedFrom = placedFrom;
        return this;
    }

    public LocalDate getPlacedTo() {
        return placedTo;
    }

    public SalesRequest setPlacedTo(LocalDate placedTo) {
        this.placedTo = placedTo;
        return this;
    }
    
    
    
    
    
}

