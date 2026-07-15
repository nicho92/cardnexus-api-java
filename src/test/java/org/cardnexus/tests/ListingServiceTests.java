package org.cardnexus.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumStatus;
import org.api.cardnexus.model.requests.ListCreationRequest;
import org.api.cardnexus.services.ListItemRequest;
import org.api.cardnexus.services.ListsServices;
import org.junit.jupiter.api.Test;

public class ListingServiceTests {

	@Test
	void listsTest() throws IOException
	{
		NexusConfig.loadTokenFromFile(new File("C:\\Users\\nicolas.pihen\\Documents\\Apps\\token.txt"));
		
		var service = new ListsServices();
		
		var req = new ListCreationRequest();
			req.setGame("mtg");
			req.setPublic(false);
			req.setName("Tests API List");
			req.setDescription("a api created list for java api");
			req.setStatus(EnumStatus.hold);
			
		var testList = service.createList(req);
		assertEquals(true, service.listNexusLists().contains(testList));
		
		
		var reqItem = new ListItemRequest();
			reqItem.addItem(null, 75886,EnumFinishes.Standard,"fr",1,EnumCondition.NM,500, 600);
			reqItem.addItem(null, 93034,EnumFinishes.Standard,"fr",1,EnumCondition.HP,80, 90);
			reqItem.addItem(null, 113580,EnumFinishes.Standard,"en",1,EnumCondition.MP,50, 60);
		
		var listResults = service.updateListItems(testList.getId(), reqItem);
		
		
		listResults.getItems().forEach(item->{
			System.out.println("created item = "+ item.getId() + " " + item.getName());
		});
		
		
		assertEquals(true, service.deleteList(testList.getId()));
	}
	
}
