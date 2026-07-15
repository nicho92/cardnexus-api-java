package org.api.cardnexus.model;

import java.util.List;

public class RunResult {

    	private String region;
    	private String currency;
    	private List<RunOption> options;
    	private List<User> sellers;
    	
    	
    	public String getCurrency() {
	    return currency;
	}
    	public List<RunOption> getOptions() {
	    return options;
	}
    	public String getRegion() {
	    return region;
	}
    	public List<User> getSellers() {
	    return sellers;
	}
}
