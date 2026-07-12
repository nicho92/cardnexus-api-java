package org.api.cardnexus.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.tools.RestClient;

public abstract class AbstractNexusService {

    protected Logger logger = LogManager.getLogger(getClass());
    protected RestClient client;
    
    protected static final String ROOT_GAME_ENDPOINT="/games";
    protected static final String ROOT_PRODUCT_ENDPOINT="/products";
    protected static final String ROOT_FEED_ENDPOINT="/feeds";
    protected static final String ROOT_ACCOUNT_ENDPOINT="/account";
    protected static final String ROOT_INVENTORY_ENDPOINT="/inventory";
    
   protected AbstractNexusService() {
	client = new RestClient(NexusConfig.getToken());
    }
    
}
