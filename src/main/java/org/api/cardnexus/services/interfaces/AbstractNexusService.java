package org.api.cardnexus.services.interfaces;

import org.api.cardnexus.client.ApiClient;
import org.api.cardnexus.client.Configuration;
import org.api.cardnexus.client.auth.HttpBearerAuth;

public abstract class AbstractNexusService {

    
    protected ApiClient defaultClient;

    public AbstractNexusService() {
	    defaultClient = Configuration.getDefaultApiClient();
    	    defaultClient.setBasePath("https://public-api.cardnexus.com/v1");
    	    ((HttpBearerAuth) defaultClient.getAuthentication("bearerAuth")).setBearerToken("your token");
    }
    
}
