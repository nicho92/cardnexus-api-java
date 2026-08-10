package org.cardnexus.tests.services;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumBulkFormat;
import org.api.cardnexus.model.requests.SearchInventoryRequest;
import org.api.cardnexus.services.BulkService;
import org.junit.jupiter.api.Test;

class BulkServiceTests {

    
    
    @Test
    void testExport() throws IOException
    {
	
	NexusConfig.loadTokenFromEnv();
	
	var export = new BulkService();
	
	
	//var result =  export.bulkExport(SearchInventoryRequest.create().setGame("mtg"), EnumBulkFormat.json);
	var result = export.getJob("6a797deb834884eddb753d5a");
	
	
	
	System.out.println(result.downloadUrl());
	
	
    }
    
}
