package org.cardnexus.tests.operations;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumOperand;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.InventoryLinesRequest;
import org.api.cardnexus.model.requests.SearchInventoryRequest;
import org.api.cardnexus.model.requests.SearchProductRequest;
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
	    NexusConfig.setTempDirectory(new File("C:\\Users\\nicol\\.magicDeskCompanion\\data"));
	    
	    iService = new InventoryService();
	    pService = new ProductsService();
	    
	    CachingService.inst().cachingProducts( NexusConfig.getDefaultGameValue());
	    
	}
    
    @Test
    void searchInventoryV2() throws IOException
    {
	
	var lines = iService.inventorySearch(SearchInventoryRequest.create().setProductType(EnumOperand.and, List.of(EnumProductType.card)).setName("black"));
	lines.forEach(il->{
	    var p = pService.getProductById(il.productId());
	    	    
	    System.out.println(p.getName() + " " + p.getExpansion().code().toUpperCase());
	    System.out.println(il);
	    System.out.println(p.getPrices().get(il.finish()));
	    System.out.println("=================================");
	});
	
    }
    
    
    
    void searchInventoryCardNameV1() throws IOException
    {
	
	String search ="Lion's Eye Diamond";
	var products = pService.searchProduct(SearchProductRequest.create().setName(search).setProductTypes(EnumProductType.card).strict());
	System.out.println("results product for "+search+" : " + products.size() + " items : " + products.stream().map(p->p.getId()).toList());
	
	var lines = iService.getInventoryLines(InventoryLinesRequest.create().setProductIds(products.stream().map(ap->ap.getId()).toList()));
	
	lines.forEach(il->{
	    var p = (CardProduct)products.stream().filter(ap->ap.getId()==il.productId()).findFirst().get();
	    	    
	    System.out.println(p.getName() + " " + p.getExpansion().code().toUpperCase()+"/"+p.getPrintNumber());
	    System.out.println(il);
	    System.out.println("----------");
	    System.out.println(p.getPrices().get(il.finish()));
	    System.out.println("=================================");
	});
    }
    
}
