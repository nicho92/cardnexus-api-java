package org.cardnexus.tests;

import java.io.IOException;
import java.time.LocalDate;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.requests.HistoryRequest;
import org.api.cardnexus.services.PricesService;
import org.api.cardnexus.tools.Formatter;
import org.junit.jupiter.api.Test;


class PricesServiceTests{
    
    	@Test
    	void testPriceCardProduct() throws IOException {
	    
    		NexusConfig.loadTokenFromEnv();
    			
		var service = new PricesService();

		var eu = service.getCurrentPrice(213551).pricesByFinish().get(EnumFinishes.Standard).getCardnexus().regions().getEu();
		var condition = eu.getByCondition().get(EnumCondition.NM);
		var fr = condition.getByLanguage().get("fr");
		
		System.out.println("FR="+fr);
		
		
		var req = new HistoryRequest();
			req.setIdProduct(75886);
			req.setFinish(EnumFinishes.Standard);
			req.setMarketplace(EnumMarketPlace.cardmarket);
			req.setFrom(LocalDate.now().minusDays(364));
			req.setTo(LocalDate.now());
			
		
		service.getHistoryPrice(req).forEach(h->{
			System.out.println(Formatter.format(h.date(),false) + " " + h.marketValue());
		});
		
		
    	}
    	
    	
}
