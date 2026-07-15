package org.api.cardnexus.model;

public class Amount {

    	private Double amount;
    	private String currency;
    	
    	public Amount() {
	    // TODO Auto-generated constructor stub
	}
    	
    	
    	
	public Amount(Double amount, String currency) {
	    super();
	    this.amount = amount;
	    this.currency = currency;
	}



	public Double getAmount() {
	    return amount;
	}
	public void setAmount(Double amount) {
	    this.amount = amount;
	}
	public String getCurrency() {
	    return currency;
	}
	public void setCurrency(String currency) {
	    this.currency = currency;
	}
    	
    	@Override
    	public String toString() {
        	return getAmount() + " " + getCurrency();
    	}
    	
    	
    
}
