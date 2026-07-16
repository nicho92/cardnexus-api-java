package org.cardnexus.tests;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;

class InventoryServiceTests {

	@Test
	void listsTest() throws IOException
	{
		NexusConfig.loadTokenFromEnv();
  	  
		var service = new InventoryService();
		var serviceProduct = new ProductsService();
		
		serviceProduct.cachingProducts("mtg",true);
		
		var req = new InventoryRequest();
			req.setGame("mtg");
			req.setLimit(100);
			req.setCondition(EnumCondition.MP);
		
		serviceProduct.listExpansion("mtg"); // put in cache
		
		service.getInventoryLines(req).forEach(line->{
			var p = (CardProduct)serviceProduct.getProductById(line.productId());
				p.setExpansion(serviceProduct.getExpansionById(p.getExpansionId()));
				System.out.println(line + "/"+ line.productId() + " : " + p.getName() + " " +p.getExpansion() + "/"+p.getPrintNumber() + " "  + line.condition());
			
		});
		
	}
	
}
