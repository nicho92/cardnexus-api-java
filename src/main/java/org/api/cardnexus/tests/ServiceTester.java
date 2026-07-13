package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		NexusConfig.setFileDirectory(new File("C:\\Users\\nicol\\.magicDeskCompanion\\data"));
		
		var service = new ProductsService();
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			req.setName("liliana of the veil");
			req.setStrictTerms(true);
		var res = service.searchProduct(req);
		
		res.forEach(p->{
		    System.out.println(p.getName());
		});

			
		
	}
}
