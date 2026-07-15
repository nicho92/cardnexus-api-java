package org.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.services.InventoryService;
import org.junit.jupiter.api.Test;

public class InventoryServiceTests {

	@Test
	void listsTest() throws IOException
	{
		NexusConfig.loadTokenFromFile(new File("C:\\Users\\nicolas.pihen\\Documents\\Apps\\token.txt"));
		
		var service = new InventoryService();
		
		var req = new InventoryRequest();
		req.setGame("mtg");
		req.setLimit(200);
		req.setCondition(EnumCondition.NM);
		req.setLanguage("en");
		req.setFinish(EnumFinishes.Foil);
		
		
		service.getInventoryLines(req).forEach(line->{
			
			System.out.println(line);
		});
		
	}
	
}
