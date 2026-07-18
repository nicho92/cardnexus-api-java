package org.cardnexus.tests;

import java.io.IOException;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.MarketListRequest;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
class ProductServiceTests{

    	private ProductsService service;

	@BeforeAll
    	void init() throws IOException
    	{
	    NexusConfig.loadTokenFromEnv();
    	    service = new ProductsService();
    	
    	}
    
    
    	@Test
    	void testMarketLists() throws IOException 
    	{
    
	System.out.println("=====CardproductBySearch");
	
	var req = new MarketListRequest();
	     req.setProductId(41547);
	
	     service.listMarketListing(req).forEach(System.out::println);
	
    	}
    
    
    	@Test
    	void testSearchCardProduct() throws IOException {
		
		var req = new SearchProductRequest();
		req.setGame("mtg");
		req.setStrictTerms(true);
		req.setProductTypes(EnumProductType.card);
		req.setProductIds(List.of(75886));
		
		service.listExpansion("mtg");//caching;
		
		System.out.println("=====CardproductBySearch");
		printData(service.searchProduct(req).getFirst());
		
		System.out.println("=====CardproductById");
		printData(service.getProductById(75886));
		
		System.out.println("=====ResolveIds");
		service.resolveProductsId(EnumMarketPlace.cardmarket, List.of(890585,250569)).entrySet().forEach(m->{
		    
		System.out.println(EnumMarketPlace.cardmarket + " " + m.getKey() + " ->" + m.getValue());
		    
		});
	}
    	
    	@Test
    	void testSearchSealedProduct() throws IOException {
	    
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			req.setName("Innistrad Booster Box");
			req.setProductTypes(EnumProductType.sealed);
			
			
			
		System.out.println("=====SealedproductBySearch");
		printData(service.searchProduct(req).getFirst());
		
		
		System.out.println("=====SealedproductById");
		printData(service.getProductById(164429));
		
		
	}
    	
    	
    	
    	void printData(AbstractProduct p)
    	{
    	    if(p instanceof CardProduct card) {
    		System.out.println(card.getId() + " " + card.getName() + " " + card.getPrintNumber() +" - " + card.getRarity());
    		System.out.println(card.getExpansion());
    		System.out.println(card.getNameSlug() + " " + card.getFinishes());
    		System.out.println("Attributs " + card.getAttributes());
    		System.out.println("ScryfallID "+ card.getExternalIds().scryfallId());
    		System.out.println("MkmId "+ card.getExternalIds().cardmarket());
    		
        	    for(var f : card.getFinishes())
        	    {
        		System.out.println(f + " mkm = " +  card.getPricesByFinish().get(f).cardmarket());
        		System.out.println(f + " tcg =" +  card.getPricesByFinish().get(f).tcgplayer());
        		System.out.println(f + " nexus=" +  card.getPricesByFinish().get(f).cardnexus());
        	    }
    	    }
    	    else if(p instanceof SealedProduct sealed) 
    	    {
 		System.out.println(sealed);
 	    }

    	    System.out.println("Url= " + p.urlProduct());
    	}
    	
    	
    	
}
