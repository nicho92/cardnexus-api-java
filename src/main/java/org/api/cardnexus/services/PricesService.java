package org.api.cardnexus.services;

import java.time.LocalDate;

import org.api.cardnexus.api.PricingApi;
import org.api.cardnexus.client.ApiException;
import org.api.cardnexus.services.model.EnumFinish;
import org.api.cardnexus.services.model.EnumMarketPlace;

public class PricesService extends AbstractNexusService {

    
    	public static void main(String[] args) {
    	    
    	    
    	    new PricesService().listPrices();
    	}
    	
    	
    	
    	public void listPrices()
    	{

    	var apiInstance = new PricingApi(defaultClient);
        var productId = 50212; 
        var marketplace = EnumMarketPlace.CARDMARKET.toValue();  
        var finish = EnumFinish.Standard.name();  
        var from = LocalDate.of(2026,6,1);  
        var to = LocalDate.now();  
        try {
          var result = apiInstance.pricingGetHistory(productId, marketplace, finish, from, to);
          System.out.println(result);
        } catch (ApiException e) {
          e.printStackTrace();
        }
      }

}
    

