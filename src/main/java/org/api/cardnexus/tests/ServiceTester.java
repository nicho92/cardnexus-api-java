package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.services.NexusConfig;
import org.api.cardnexus.services.ProductsServices;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new ProductsServices();
		
		
		System.out.println(service.searchProduct("Black Lotus"));
	}
	
}
