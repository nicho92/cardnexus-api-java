package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.services.inventory.InventoryService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new InventoryService();
		var req = new InventoryRequest();
        		req.setLanguage("fr");
        		req.setGame("mtg");
        		req.setCondition(EnumCondition.MP);
		
		service.getInventoryLines(req);

	}
	
}
