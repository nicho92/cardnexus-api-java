package org.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;

public class InventoryServiceTests {

	@Test
	void listsTest() throws IOException
	{
		NexusConfig.loadTokenFromFile(new File("C:\\Users\\nicolas.pihen\\Documents\\Apps\\token.txt"));
		
		var service = new InventoryService();
		var serviceProduct = new ProductsService();
		
		serviceProduct.cachingProducts("mtg",false);
		
		var req = new InventoryRequest();
		req.setGame("mtg");
		req.setLimit(100);
		req.setCondition(EnumCondition.MP);
		
		
		serviceProduct.listExpansion("mtg"); // put in cache
		
		service.getInventoryLines(req).forEach(line->{
			var p = (CardProduct)serviceProduct.getProductById(line.getProductId());
			try {
				
				p.setExpansion(serviceProduct.getExpansionById(p.getExpansionId()));
				
				System.out.println(line + "/"+ line.getProductId() + " : " + p.getName() + " " +p.getExpansion() + "/"+p.getPrintNumber() + " "  + line.getCondition());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}
	
}
