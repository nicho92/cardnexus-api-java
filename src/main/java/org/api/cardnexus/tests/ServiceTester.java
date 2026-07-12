package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.requests.HistoryRequest;
import org.api.cardnexus.services.PricesService;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new PricesService();
		var req = new HistoryRequest();
		req.setIdProduct(213551);
		req.setFrom(LocalDate.now(ZoneId.systemDefault()).minusMonths(6));
		
		var prices = service.getHistoryPrice(req);
		
		
	}
	
}
