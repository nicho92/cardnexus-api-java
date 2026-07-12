package org.api.cardnexus.services.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.tools.RestClient;

public abstract class AbstractNexusService {

    protected Logger logger = LogManager.getLogger(getClass());
    protected RestClient client;

    
   protected AbstractNexusService() {
	client = new RestClient(NexusConfig.getToken());
    }
    
}
