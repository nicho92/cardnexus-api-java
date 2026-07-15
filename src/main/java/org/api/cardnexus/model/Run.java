package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumRunStatus;

public class Run {
    
    	private String id;
    	private EnumRunStatus status;
    	private Progress progress;
	public String getId() {
	    return id;
	}
	public void setId(String id) {
	    this.id = id;
	}
	public EnumRunStatus getStatus() {
	    return status;
	}
	public void setStatus(EnumRunStatus status) {
	    this.status = status;
	}
	public Progress getProgress() {
	    return progress;
	}
	public void setProgress(Progress progress) {
	    this.progress = progress;
	}
    	
    	
    	
}
