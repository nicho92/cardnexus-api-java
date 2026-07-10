package org.api.cardnexus.services.interfaces;

import org.api.cardnexus.client.ApiClient;
import org.api.cardnexus.client.Configuration;
import org.api.cardnexus.client.auth.HttpBearerAuth;
import org.api.cardnexus.services.NexusConfig;

public abstract class AbstractNexusService {

    
    protected ApiClient defaultClient;

    public AbstractNexusService() {
	    defaultClient = Configuration.getDefaultApiClient();
    	defaultClient.setBasePath("https://public-api.cardnexus.com/v1");
    	setBearer(NexusConfig.getToken());
    }
    
    
    private void setBearer(String token)
    {
    	 ((HttpBearerAuth) defaultClient.getAuthentication("bearerAuth")).setBearerToken(token);
    }
    
}
