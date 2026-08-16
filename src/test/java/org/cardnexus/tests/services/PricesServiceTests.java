package org.cardnexus.tests.services;

import java.io.IOException;
import java.time.LocalDate;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.requests.HistoryRequest;
import org.api.cardnexus.services.PricesService;
import org.api.cardnexus.tools.Utils;
import org.junit.jupiter.api.Test;


class PricesServiceTests{
    
    	@Test
    	void testPriceCardProduct() throws IOException {
	    
    		NexusConfig.loadTokenFromEnv();
    			
		var service = new PricesService();

		var eu = service.getCurrentPrice(213551).pricesByFinish().get(EnumFinishes.Standard).cardnexus();
		System.out.println(eu);
		
		
		var req = HistoryRequest.create().setIdProduct(75886).setFinish(EnumFinishes.Standard).setMarketplace(EnumMarketPlace.cardmarket).setFrom(LocalDate.now().minusDays(30)).setTo(LocalDate.now());
			
		
		service.getHistoryPrice(req).forEach(p->{
		    
		    System.out.println(Utils.format(p.date(), false) + " " + Utils.format(p.marketValue()));
		    
		});
				
		
		System.out.println(service.getLastSales(108703));
		
    	}
    	
    	
}
