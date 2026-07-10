package org.api.cardnexus.services;

import java.time.LocalDate;
import java.util.List;

import org.api.cardnexus.api.PricingApi;
import org.api.cardnexus.client.ApiException;
import org.api.cardnexus.model.PricingGetHistory200ResponseDataInner;
import org.api.cardnexus.model.PricingGetHistory200ResponseDataInner.FinishEnum;
import org.api.cardnexus.model.PricingGetProductPrices200Response;
import org.api.cardnexus.model.PricingGetSales200Response;
import org.api.cardnexus.model.ProductsResolveProductsRequest.MarketplaceEnum;
import org.api.cardnexus.services.interfaces.AbstractNexusService;

public class PricesService extends AbstractNexusService {
		private PricingApi apiInstance;
    	
    	
    	public PricesService() {
    	    apiInstance = new PricingApi(defaultClient);
    	}
    	
    	public List<PricingGetHistory200ResponseDataInner> listPrices(Integer productId, MarketplaceEnum market, FinishEnum finish) throws ApiException
    	{
            var from = LocalDate.now().minusMonths(1);  
            var to = LocalDate.now();  
       
              return apiInstance.pricingGetHistory(productId, market.getValue(), finish.getValue(), from, to).getData();
       
        }
    	
    	
    	public PricingGetProductPrices200Response getProductPrice(Integer productId) throws ApiException
    	{
            return apiInstance.pricingGetProductPrices(productId);
       
        }
    	
    	public PricingGetSales200Response getSales(Integer productId) throws ApiException
    	{
            return apiInstance.pricingGetSales(productId, null, null, null, null, null);
       
        }
    	
    	
    	
}
    

