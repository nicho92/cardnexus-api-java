package org.api.cardnexus.model.requests;

import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;




public class CardOptimizationRequest {


    private ProductEntryCountry destination;
    private List<ProductEntry> targets;
    
    public CardOptimizationRequest() {
	targets = new ArrayList<ProductEntry>();
    }
    
    public void setCountry(String countryCode)
    {
	destination = new ProductEntryCountry(countryCode);
    }
    
    public void addEntry(int productId, int quantity,EnumCondition minCondition,EnumFinishes finish, List<String> languages,Amount maxUnitPrice)
    {
	targets.add(new ProductEntry(productId, quantity, minCondition, finish, languages, maxUnitPrice));
    }

record ProductEntry( int productId, int quantity,EnumCondition minCondition,EnumFinishes finish, List<String> languages,Amount maxUnitPrice) {}

record ProductEntryCountry (String country){}
    
    
}

