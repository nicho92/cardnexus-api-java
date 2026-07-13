package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		NexusConfig.setFileDirectory(new File("C:\\Users\\nicol\\.magicDeskCompanion\\data"));
		
		var service = new ProductsService();
		
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			//req.setNameSlug("mountain");
			req.setName("Mountain");
		
		service.searchProduct(req);
		
		
	}
}
