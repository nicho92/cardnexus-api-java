package org.api.cardnexus.model.account;

import java.util.Date;

public class Vacation {

    private boolean enabled;
    private String status;
    private Date since;
    
    
    @Override
    public String toString() {
           return getStatus();
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getSince() {
        return since;
    }
    public void setSince(Date since) {
        this.since = since;
    }
    
    
    
    
    
}
