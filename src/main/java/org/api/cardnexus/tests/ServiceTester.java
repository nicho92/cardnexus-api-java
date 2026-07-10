package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.services.NexusConfig;
import org.api.cardnexus.services.ProductsServices;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("C:\\Users\\nicolas.pihen\\Downloads\\token.txt"));
		
		var service = new ProductsServices();
	
		
//		priceService.listPrices(50212, MarketplaceEnum.CARDMARKET, FinishEnum.STANDARD).forEach(p->{
//	    	System.out.println(p);
//	    });
//		
		
		System.out.println(	service.getProduct("50212"));
	}
	
}
