package org.cardnexus.tests.services;

import java.io.IOException;
import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumKindsRun;
import org.api.cardnexus.model.requests.CardOptimizationRequest;
import org.api.cardnexus.services.CartService;
import org.junit.jupiter.api.Test;

class CartServiceTests {

    @Test
    void testOptimizer() throws IOException
    {
	

	NexusConfig.loadTokenFromEnv();
	
	var serv = new CartService();
	
	var optiRequest = CardOptimizationRequest.create()
						.addEntry(75886, 1, EnumCondition.MP,EnumFinishes.Standard, List.of("fr","en"),new Amount(800.0,"EUR"))
						.addEntry(93034, 1, EnumCondition.MP,EnumFinishes.Standard, List.of("fr","en"),new Amount(150.0,"EUR"))
						.addEntry(113580, 1, EnumCondition.MP,EnumFinishes.Standard, List.of("fr","en"),new Amount(150.0,"EUR"))
						.setCountry("fr");
	
	
	var id = serv.runOptimizationQuery(optiRequest);
	
	var r = serv.getRunById(id);
	
	var cart = serv.applyRunsToCart(r.id(), EnumKindsRun.lowest_price);
	
	System.out.println(cart);
	
    }
    
}
