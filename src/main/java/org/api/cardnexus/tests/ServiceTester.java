package org.	api.cardnexus.tests;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.enums.EnumMarketCurrency;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		
		var service = new ProductsService();
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			req.setName("Conflux");
			req.setProductTypes(EnumProductType.sealed);
			req.setStrictTerms(false);
			
		var res = service.searchProduct(req);
		
		res.forEach(p->{
		    if(p instanceof SealedProduct s)
			System.out.println(p.getId() + " " + p.getName() + " " + s.getExpansion().getCode().toUpperCase() + "/"+  s.getProductCategory() + " -> " + s.getPrices(null, EnumMarketCurrency.eur));
		    
		});

		
		
		service.cachingProducts("mtg");
		
		
	}
}
