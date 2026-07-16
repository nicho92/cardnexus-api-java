package org.cardnexus.tests;

import java.io.IOException;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;
import org.junit.jupiter.api.Test;


class ProductServiceTests{
    
    	@Test
    	void testSearchCardProduct() throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		
		var service = new ProductsService();
		
		var req = new SearchProductRequest();
		req.setGame("mtg");
		req.setStrictTerms(true);
		req.setProductTypes(EnumProductType.card);
		
		req.setProductIds(List.of(75886));
		
		System.out.println("=====CardproductBySearch");
		printData(service.searchProduct(req).getFirst());
		
		System.out.println("=====CardproductById");
		printData(service.getProductById(75886));
		
		System.out.println("=====ResolveIds");
		service.resolveProductsId(EnumMarketPlace.cardmarket, List.of(890585,250569)).entrySet().forEach(m->{
		    
		System.out.println(EnumMarketPlace.cardmarket + " " + m.getKey() + " ->" + m.getValue());
		    
		});
		
	}
    	
    	void printData(AbstractProduct p)
    	{
    	    if(p instanceof CardProduct card) {
    		System.out.println(card.getId() + " " + card.getName() + " " + card.getExpansion()+"/"+card.getPrintNumber() +" - " + card.getRarity());
    		System.out.println(card.getNameSlug() + " " + card.getFinishes());
    		System.out.println("Types " + card.getAttributes().type());
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
 		System.out.println(sealed.getId() + " " + sealed.getName() + " " + sealed.getExpansion());
 		System.out.println("Types " + sealed.getProductCategory());
 		System.out.println("MkmID "+ sealed.getExternalIds().cardmarket());
 		System.out.println("NexusPrice "+ sealed.getPrices().cardnexus());
	    
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
			
			
			
		System.out.println("=====SealedproductBySearch");
		printData(service.searchProduct(req).getFirst());
		
		
		System.out.println("=====SealedproductById");
		printData(service.getProductById(164429));
		
		
	}
    	
    	
    	
}
