package org.cardnexus.tests;

import java.io.IOException;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;


class ServiceTester{
    
    	@Test
    	void testSearchCardProduct() throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		
		var service = new ProductsService();
		var req = new SearchProductRequest()
			.setGame("mtg")
			.setStrictTerms(true)
			.setProductTypes(EnumProductType.card)
			.setExpansionId(1187)
			.setProductIds(List.of(213551));
		
		System.out.println("=====productBySearch");
		printData(service.searchProduct(req).getFirst());
		
		System.out.println("=====productById");
		printData(service.getProductById(213551));
			
	}
    	
    	void printData(AbstractProduct p)
    	{
    	    if(p instanceof CardProduct card) {
    		System.out.println(card.getId() + " " + card.getName() + " " + card.getExpansion()+"/"+card.getPrintNumber() +" - " + card.getRarity());
    		System.out.println(card.getNameSlug() + " " + card.getFinishes());
    		System.out.println("Types " + card.getAttributes().getTypes());
    		System.out.println("ScryfallID "+ card.getExternalIds().getScryfallId());
    		System.out.println("Mkm "+ card.getExternalIds().getCardmarket());
	    
        	    for(var f : card.getFinishes())
        	    {
        		System.out.println(f + " mkm = " +  card.getPricesByFinish().get(f).getCardmarket());
        		System.out.println(f + " tcg =" +  card.getPricesByFinish().get(f).getTcgplayer());
        		System.out.println(f + " nexus=" +  card.getPricesByFinish().get(f).getCardnexus());
        		
        	    }
    	    }
    	    else if(p instanceof SealedProduct sealed) 
    	    {
 		System.out.println(sealed.getId() + " " + sealed.getName() + " " + sealed.getExpansion());
 		System.out.println("Types " + sealed.getProductCategory());
 		System.out.println("MkmID "+ sealed.getExternalIds().getCardmarket());
 		System.out.println("Prices "+ sealed.getPrices().getCardmarket());
	    
 	    }
    	    
    	}
    	
    	
    	@Test
    	void testSearchSealedProduct() throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		
		
		var service = new ProductsService();
			
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			req.setName("Innistrad Booster Box");
			req.setProductTypes(EnumProductType.sealed);
			
			
			
		System.out.println("=====productBySearch");
		printData(service.searchProduct(req).getFirst());
		
			
	}
    	
    	
    	
}
