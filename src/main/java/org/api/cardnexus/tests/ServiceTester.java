package org.api.cardnexus.tests;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.services.ListItemRequest;
import org.api.cardnexus.services.ListsServices;

public class ServiceTester{

	public static void main(String[] args) throws IOException {
	    
		NexusConfig.loadTokenFromFile(new File("E:\\Mon Drive\\token.txt"));
		
		var service = new ListsServices();
		
		var req = new ListItemRequest();
		     req.addItem(null, 71044, EnumFinishes.Standard, "en", 4, EnumCondition.NM, 0, 60);
		     req.addItem(null, 50212, EnumFinishes.Standard, "en", 2, EnumCondition.NM, 0, 180);
		     
		     
		
		var list = service.updateListItems("6a53ea7b253f64612c79abc3",req);
		System.out.println(list);
		
	}
	
}
