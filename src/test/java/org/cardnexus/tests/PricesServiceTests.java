package org.cardnexus.tests;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.services.PricesService;
import org.junit.jupiter.api.Test;


class PricesServiceTests{
    
    	@Test
    	void testPriceCardProduct() throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		
		var service = new PricesService();

		var eu = service.getCurrentPrice(213551).getPricesByFinish().get(EnumFinishes.Standard).getCardnexus().getRegions().getEu();
		
		System.out.println("EU=" + eu.getCurrency());
		
		var condition = eu.getByCondition().get(EnumCondition.NM);
		
		System.out.println("NM=" + condition);
		
		var fr = condition.getByLanguage().get("fr");
		
		System.out.println("FR="+fr);
		
		
    	}
    	
    	
}
