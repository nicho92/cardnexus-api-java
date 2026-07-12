package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFeedKey;
import org.api.cardnexus.services.FeedsService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new FeedsService();
		
		service.download("mtg", EnumFeedKey.catalog);
		
	}
	
}
