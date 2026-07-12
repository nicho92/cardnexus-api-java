package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.services.PricesService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new PricesService();
		
		
		var price = service.getCurrentPrice(213551);
		
		
		
		price.getCardNexus().entrySet().forEach(e->{
		    System.out.println(e.getKey() + " " + e.getValue());
		});
		
	}
	
}
