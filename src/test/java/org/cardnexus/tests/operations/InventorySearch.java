package org.cardnexus.tests.operations;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;

class InventorySearch {
    
    
    
    
    @Test
    void searchInventoryCardName() throws IOException
    {
	
	String search ="Lion's Eye Diamond";
	
	NexusConfig.loadTokenFromEnv();
	NexusConfig.DEFAULT_GAME_VALUE="mtg";
	
	
	var iService = new InventoryService();
	var pService = new ProductsService();
	
	var req2 = new SearchProductRequest();
	req2.setName(search);
	req2.setStrictTerms(true);

	var products = pService.searchProduct(req2);
	
	var req = new InventoryRequest();
	req.setProductIds(products.stream().map(ap->ap.getId()).toList());
	
	var lines = iService.getInventoryLines(req);
	
	lines.forEach(il->{
	    
	    var p = (CardProduct)products.stream().filter(ap->ap.getId()==il.productId()).findFirst().get();
	    
	    	    
	    System.out.println(p.getName() + " " + p.getExpansion().code());
	    System.out.println(il);
	    System.out.println(p.getPricesByFinish().get(il.finish()));
	    System.out.println("=================================");
	    
	    
	});
    }
    
}
