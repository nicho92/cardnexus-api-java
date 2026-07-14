package org.cardnexus.tests;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.services.PricesService;
import org.junit.jupiter.api.Test;


class PricesServiceTests{
    
    	@Test
    	void testPriceCardProduct() throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		
		var service = new PricesService();
		    	
		
		System.out.println(service.getCurrentPrice(213551).getPricesByFinish().get(EnumFinishes.Standard).getCardnexus().getRegions().getEu());
		
    	}
    	
    	
}
