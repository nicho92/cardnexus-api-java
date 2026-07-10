package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.model.PricingGetHistory200ResponseDataInner.FinishEnum;
import org.api.cardnexus.model.ProductsResolveProductsRequest.MarketplaceEnum;
import org.api.cardnexus.services.NexusConfig;
import org.api.cardnexus.services.PricesService;

public class PriceTests {

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("C:\\Users\\nicolas.pihen\\Downloads\\token.txt"));
		
	    new PricesService().listPrices(50212, MarketplaceEnum.CARDMARKET, FinishEnum.STANDARD).forEach(p->{
	    	System.out.println(p);
	    });
	}
	
}
