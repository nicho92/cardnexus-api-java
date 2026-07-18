package org.cardnexus.tests.operations;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;

class InventorySearch {
    
    
    
    
    @Test
    void searchInventoryCardName() throws IOException
    {
	
	String search ="Swamp";
	
	NexusConfig.loadTokenFromEnv();
	NexusConfig.DEFAULT_GAME_VALUE="mtg";
	
	
	var iService = new InventoryService();
	var pService = new ProductsService();
	
	var req2 = new SearchProductRequest();
	req2.setName(search);
	req2.setStrictTerms(true);

	var req = new InventoryRequest();
	req.setProductIds(pService.searchProduct(req2).stream().map(ap->ap.getId()).toList());
	
	var lines = iService.getInventoryLines(req);
	
	System.out.println("found "+lines.size() +" in inventory");
	
	
	lines.forEach(System.out::println);
    }
    
}
