package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketCurrency;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new ProductsService();
		
		var req = new SearchProductRequest();
		req.setNameSlug("mirage-booster-pack");
		req.addGameFilter("game", "mtg");
		
		service.searchProduct(req).forEach(p->{
		    System.out.println(p.getProductType() + " " + p.getName() + " " + p.getExpansion() + " "+ p.getClass().getSimpleName());
		    
		    System.out.println( p.getPrices(EnumFinishes.Standard,EnumMarketCurrency.eur).getMarketValue());
			
		});
	}
	
}
