package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketCurrency;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;
import org.api.cardnexus.tools.Slugifyer;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromEnv();
		NexusConfig.setFileDirectory(new File("C:\\Users\\nicol\\.magicDeskCompanion\\data"));
		
		var service = new ProductsService();
		
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			req.setNameSlug(Slugifyer.nameSlug("Liliana of the Veil","mtg"));
			req.setProductTypes(EnumProductType.card);
			
		service.searchProduct(req).forEach(p->{
		    System.out.println(p.getName() + " / " + p.getExpansion());
		    System.out.println("--->" +p.getPrices(EnumFinishes.Standard, EnumMarketCurrency.eur));
		});
		
		
	}
}
