package org.cardnexus.tests.services;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumBulkFormat;
import org.api.cardnexus.model.requests.SearchInventoryRequest;
import org.api.cardnexus.services.BulkService;
import org.junit.jupiter.api.Test;

public class BulkServiceTests {

    
    
    @Test
    void testExport() throws IOException
    {
	
	NexusConfig.loadTokenFromEnv();
	
	var export = new BulkService();
	
	
	var result =  export.bulkExport(SearchInventoryRequest.create().setGame("mtg"), EnumBulkFormat.csv);
	
	
	result = export.getJob(result.id());
	
	System.out.println(result);
	
	
    }
    
}
