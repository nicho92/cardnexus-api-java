package org.cardnexus.tests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumStatus;
import org.api.cardnexus.model.requests.ListCreationRequest;
import org.api.cardnexus.model.requests.ListItemRequest;
import org.api.cardnexus.services.ListsServices;
import org.junit.jupiter.api.Test;

class ListingServiceTests {

	@Test
	void listsTest() throws IOException
	{
  		NexusConfig.loadTokenFromEnv();
  	  
		
		var service = new ListsServices();
		
		var req = ListCreationRequest.create()
			.setGame("mtg")
			.setPublic(false)
			.setName("Tests API List")
			.setDescription("a api created list for java api")
			.setStatus(EnumStatus.hold);
			
		var testList = service.createList(req);
		assertEquals(true, service.listNexusLists().contains(testList));
		
		var reqItem = ListItemRequest.create()
			.addItem(null, 75886,EnumFinishes.Standard,"fr",1,EnumCondition.NM,500, 600)
			.addItem(null, 93034,EnumFinishes.Standard,"fr",1,EnumCondition.HP,80, 90)
			.addItem(null, 113580,EnumFinishes.Standard,"en",1,EnumCondition.MP,50, 60);
		
		var listResults = service.updateListItems(testList.id(), reqItem);
		
		assertEquals(listResults.items().size(), 3);
		
		listResults.items().forEach(item->{
			System.out.println(item);
		});
		
		var ret = service.deleteList(testList.id());
		System.out.println("Delete List Result: "+ret);
		assertEquals(true, ret);
		
	}
	
}
