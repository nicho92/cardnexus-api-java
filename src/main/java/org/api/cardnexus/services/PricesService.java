package org.api.cardnexus.services;

import java.time.LocalDate;
import java.util.List;

import org.api.cardnexus.api.PricingApi;
import org.api.cardnexus.client.ApiException;
import org.api.cardnexus.model.PricingGetHistory200ResponseDataInner;
import org.api.cardnexus.model.PricingGetHistory200ResponseDataInner.FinishEnum;
import org.api.cardnexus.services.interfaces.AbstractNexusService;
import org.api.cardnexus.services.model.EnumMarketPlace;

public class PricesService extends AbstractNexusService {

    
    	public static void main(String[] args) {
    	    
    	    new PricesService().listPrices(50212, EnumMarketPlace.CARDMARKET, FinishEnum.STANDARD);
    	}



	private PricingApi apiInstance;
    	
    	
    	public PricesService() {
    	    apiInstance = new PricingApi(defaultClient);
	}
    	
    	public List<PricingGetHistory200ResponseDataInner> listPrices(Integer productId, EnumMarketPlace market, FinishEnum finish) throws ApiException
    	{
            var from = LocalDate.of(2026,6,1);  
            var to = LocalDate.now();  
       
              return apiInstance.pricingGetHistory(productId, market.toValue(), finish.getValue(), from, to).getData();
       
        }
    	
}
    

