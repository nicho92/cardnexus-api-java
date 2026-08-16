package org.api.cardnexus.model;

import java.io.IOException;

public class NexusAPIException extends IOException{
    
    private final NexusError error;
    
    
    public NexusAPIException(NexusError error) {
	this.error = error;
    }
    
    public NexusError getError() {
	return error;
    }
    
    @Override
    public String getMessage() {
       return error.message();
    }
    

    private static final long serialVersionUID = 1L;

    
    
    
}
