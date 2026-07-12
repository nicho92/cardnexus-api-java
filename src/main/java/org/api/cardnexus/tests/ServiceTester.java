package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new ProductsService();
		
		

		var req = new SearchProductRequest();
		req.setNameSlug("forest");
		req.addGameFilter("game", "mtg");
		req.setProductTypes(EnumProductType.card);

		System.out.println(service.searchProduct(req).size());
		
		
		
	}
	
}
