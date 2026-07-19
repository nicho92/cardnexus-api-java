package org.cardnexus.tests.operations;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.InventoryRequest;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;

class InventorySearchTest {
    
    @Test
    void searchInventoryCardName() throws IOException
    {
	
	String search ="Lion's Eye Diamond";
	
	
	
	NexusConfig.loadTokenFromEnv();
	NexusConfig.DEFAULT_GAME_VALUE="mtg";
	
	var iService = new InventoryService();
	var pService = new ProductsService();
	
	var products = pService.searchProduct(SearchProductRequest.create().setName(search).setProductTypes(EnumProductType.card).strict());
	System.out.println("results product for "+search+" : " + products.size() + " items : " + products.stream().map(p->p.getId()).toList());
	
	var lines = iService.getInventoryLines(InventoryRequest.create().setProductIds(products.stream().map(ap->ap.getId()).toList()));
	
	lines.forEach(il->{
	    var p = (CardProduct)products.stream().filter(ap->ap.getId()==il.productId()).findFirst().get();
	    	    
	    System.out.println(p.getName() + " " + p.getExpansion().code().toUpperCase()+"/"+p.getPrintNumber());
	    System.out.println(il);
	    System.out.println("----------");
	    System.out.println(p.getPricesByFinish().get(il.finish()));
	    System.out.println("=================================");
	});
    }
    
}
