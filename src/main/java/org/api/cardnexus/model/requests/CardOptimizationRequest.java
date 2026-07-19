package org.api.cardnexus.model.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class CardOptimizationRequest {


    private ProductEntryCountry destination;
    private List<ProductEntry> targets;
    
    public static CardOptimizationRequest create()
    {
	return new CardOptimizationRequest();
    }
    
    
    private CardOptimizationRequest() {
    	targets = new ArrayList<ProductEntry>();
    	destination = new ProductEntryCountry(Locale.getDefault().getCountry());
    }
    
    public CardOptimizationRequest setCountry(String countryCode)
    {
    	destination = new ProductEntryCountry(countryCode);
    	return this;
    }
    
    public CardOptimizationRequest addEntry(int productId, int quantity,EnumCondition minCondition,EnumFinishes finish, List<String> languages,Amount maxUnitPrice)
    {
	targets.add(new ProductEntry(productId, quantity, minCondition, finish, languages, maxUnitPrice));
	return this;
    }

record ProductEntry( int productId, int quantity,EnumCondition minCondition,EnumFinishes finish, List<String> languages,Amount maxUnitPrice) {}

record ProductEntryCountry (String country){}
    
    
}

