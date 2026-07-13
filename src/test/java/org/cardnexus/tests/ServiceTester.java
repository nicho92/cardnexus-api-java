package org.cardnexus.tests;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketCurrency;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;


class ServiceTester{
    	@Test
    	void testSearchProduct() throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		
		var service = new ProductsService();
			
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			req.setName("Thanos, the Mad Titan");
			req.setStrictTerms(true);
			req.setProductTypes(EnumProductType.card);
			
		service.searchProduct(req).forEach(p->{
		    var card = (CardProduct)p;
		    System.out.println(card.getName() + " " + card.getExpansion()+"/"+card.getPrintNumber() + " " + card.getPrices(EnumFinishes.Foil, EnumMarketCurrency.eur));
		    
		});
		
		
		
		
	}
}
