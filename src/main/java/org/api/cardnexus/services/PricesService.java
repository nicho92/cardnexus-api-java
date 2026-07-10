package org.api.cardnexus.services;

import java.time.LocalDate;

import org.api.cardnexus.api.PricingApi;
import org.api.cardnexus.client.ApiException;
import org.api.cardnexus.services.interfaces.AbstractNexusService;
import org.api.cardnexus.services.model.EnumFinish;
import org.api.cardnexus.services.model.EnumMarketPlace;

public class PricesService extends AbstractNexusService {

    
    	public static void main(String[] args) {
    	    
    	    new PricesService().listPrices(50212, EnumMarketPlace.CARDMARKET, EnumFinish.Standard);
    	}



	private PricingApi apiInstance;
    	
    	
    	public PricesService() {
    	    apiInstance = new PricingApi(defaultClient);
	}
    	
    	
    	
    	public void listPrices(Integer productId, EnumMarketPlace market, EnumFinish finish)
    	{
    	
        var from = LocalDate.of(2026,6,1);  
        var to = LocalDate.now();  
        try {
          var result = apiInstance.pricingGetHistory(productId, market.toValue(), finish.name(), from, to);
          System.out.println(result);
        } catch (ApiException e) {
          e.printStackTrace();
        }
      }

}
    

