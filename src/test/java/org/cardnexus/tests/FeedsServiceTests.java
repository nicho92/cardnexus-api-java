package org.cardnexus.tests;

import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.services.FeedsService;
import org.junit.jupiter.api.Test;

class FeedsServiceTests {

    @Test
    void listFeeds() throws IOException
    {
		
	NexusConfig.loadTokenFromEnv();
	
	
	var service =new FeedsService();
	
	
	service.getGameFeeds("mtg").entrySet().forEach(e->{
	    
	    System.out.println(e.getKey());
	    System.out.println("    " + e.getValue());
	 
	});
	
	
	
    }
    
}
