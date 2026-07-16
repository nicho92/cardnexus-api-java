package org.cardnexus.tests;

import java.io.IOException;
import java.time.LocalDate;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.requests.HistoryRequest;
import org.api.cardnexus.services.PricesService;
import org.junit.jupiter.api.Test;


class PricesServiceTests{
    
    	@Test
    	void testPriceCardProduct() throws IOException {
	    
    		NexusConfig.loadTokenFromEnv();
    			
		var service = new PricesService();

		var eu = service.getCurrentPrice(213551).pricesByFinish().get(EnumFinishes.Standard).cardnexus();
		System.out.println(eu);
		
		
		var req = new HistoryRequest();
			req.setIdProduct(75886);
			req.setFinish(EnumFinishes.Standard);
			req.setMarketplace(EnumMarketPlace.tcgplayer);
			req.setFrom(LocalDate.now().minusDays(364));
			req.setTo(LocalDate.now());
			
		
		service.getHistoryPrice(req).forEach(h->{
			System.out.println(h);
		});
		
		
    	}
    	
    	
}
