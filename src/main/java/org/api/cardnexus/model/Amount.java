package org.api.cardnexus.model;

public record Amount(Double amount, String currency)
{
    	
    	@Override
    	public String toString() {
        	return amount() + " " + currency();
    	}
}
