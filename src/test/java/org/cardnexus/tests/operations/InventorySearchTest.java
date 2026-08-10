package org.cardnexus.tests.operations;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumOperand;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchInventoryRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.api.cardnexus.tools.CachingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class InventorySearchTest {
    

    InventoryService iService;
    ProductsService pService;
	
    
    @BeforeAll
	void init() throws IOException
	{
	    NexusConfig.loadTokenFromEnv();
	    NexusConfig.setDefaultGameValue("mtg");
	    
	    iService = new InventoryService();
	    pService = new ProductsService();
	    
	    CachingService.inst().cachingProducts( NexusConfig.getDefaultGameValue());
	    
	}
    
    @Test
    void searchInventoryV2() throws IOException
    {
	
	var lines = iService.inventorySearch(SearchInventoryRequest.create()
								.setProductType(EnumOperand.and, EnumProductType.card)
								.setFinish(EnumOperand.and, EnumFinishes.Foil)
							   );
	lines.forEach(il->{
	    var p = pService.getProductById(il.productId());
	    	    
	    System.out.println(p.getName() + " " + p.getExpansion().code().toUpperCase());
	    System.out.println(il);
	    System.out.println(p.getPrices().get(il.finish()));
	    System.out.println("=================================");
	});
	
    }
    
    
}
