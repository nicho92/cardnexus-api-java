package org.cardnexus.tests.services;

import java.io.IOException;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.InventoryLine;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumOperand;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.InventoryLinesRequest;
import org.api.cardnexus.model.requests.SearchInventoryRequest;
import org.api.cardnexus.model.requests.UpdateInventoryRequest;
import org.api.cardnexus.services.InventoryService;
import org.api.cardnexus.services.ProductsService;
import org.api.cardnexus.tools.CachingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
class InventoryServiceTests {
    
    private InventoryService service;
    private ProductsService serviceProduct;

    	@BeforeAll
	void init() throws IOException
	{
	    NexusConfig.loadTokenFromEnv();
	    NexusConfig.setDefaultGameValue("mtg");
	    
	    service = new InventoryService();
	    serviceProduct = new ProductsService();
	    
	    CachingService.inst().cachingProducts( NexusConfig.getDefaultGameValue());

	}
	  @Test
	    void searchInventoryV2() throws IOException
	    {
	      
	      var ids=serviceProduct.listExpansion(NexusConfig.getDefaultGameValue()).stream().filter(exp->exp.name().equalsIgnoreCase("Mirage")).map(Expansion::id).toList();
	      
	      
		var lines = service.inventorySearch(SearchInventoryRequest.create()
									.setProductType(EnumOperand.and, EnumProductType.card)
									.setExpansionId(ids)
									.setFinish(EnumOperand.and, EnumFinishes.Standard)
								   );
		lines.forEach(il->{
		    var p = serviceProduct.getProductById(il.productId());
		    	    
		    System.out.println(p.getName() + " " + p.getExpansion().code().toUpperCase());
		    System.out.println(il);
		    System.out.println(p.getPrices().get(il.finish()).cardmarket().marketValue());
		    System.out.println("=================================");
		});
		
	    }
   
    	void addInventoryEntries() throws IOException
    	{
    	    var iline2 = new InventoryLine(null, null, "test creation", 44269, "mtg", EnumFinishes.Standard, EnumCondition.LP, "en", 1, false, null, null, null, null, null);
    	    var iline = new InventoryLine(null, null, "test creation", 44269, "mtg", EnumFinishes.Standard, EnumCondition.LP, "fr", 1, false, null, null, null, null, null); // this product doesn't exist in this language
    	    var results = service.addInventoryLines(List.of(iline,iline2));
    	    
    	    System.out.println(results);
    	    
    	}
    	
    	void listsTest() throws IOException
	{
		var req =InventoryLinesRequest.create().setCondition(EnumCondition.MP);
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
