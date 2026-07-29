package org.cardnexus.tests.services;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.requests.InventoryLinesRequest;
import org.api.cardnexus.model.requests.UpdateInventoryRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
class InventoryServiceTests {
    
    private InventoryService service;

    @BeforeAll
	void init() throws IOException
	{
	    NexusConfig.loadTokenFromEnv();
	    NexusConfig.DEFAULT_GAME_VALUE="mtg";
	    service = new InventoryService();
	}
    
    	@Test
    	void listsTest() throws IOException
	{
		var serviceProduct = new ProductsService();
		
		serviceProduct.cachingProducts( NexusConfig.DEFAULT_GAME_VALUE,true);
		
		var req =InventoryLinesRequest.create().setCondition(EnumCondition.MP);
		
		serviceProduct.listExpansion(NexusConfig.DEFAULT_GAME_VALUE); // put in cache
		
		service.getInventoryLines(req).forEach(line->{
			var p = (CardProduct)serviceProduct.getProductById(line.productId());
			     p.setExpansion(serviceProduct.getExpansionById(p.getExpansionId()));
			     System.out.println(line + "/"+ line.productId() + " : " + p.getName() + " " +p.getExpansion() + "/"+p.getPrintNumber() + " "  + line.condition());
		});
	}
    	
    	
	void lineManipulation() throws IOException
	{
	    var line = service.getInventoryLine("69d3e1663e676d07d4cd36ce");
	    System.out.println(line);
	    
	    var req = UpdateInventoryRequest.create().changeQuantity(1).setComment("update from my api");
	    line= service.updateInventoryLine(line.id(), req);
	    System.out.println(line);
	    
	}
	
	
	
}
