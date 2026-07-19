package org.api.cardnexus.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.listener.URLCallListener;
import org.api.cardnexus.tools.RestClient;

public abstract class AbstractNexusService {

    protected Logger logger = LogManager.getLogger(getClass());
    protected RestClient client;
    
    protected static final String ROOT_GAME_ENDPOINT="/games";
    protected static final String ROOT_PRODUCT_ENDPOINT="/products";
    protected static final String ROOT_FEED_ENDPOINT="/feeds";
    protected static final String ROOT_ACCOUNT_ENDPOINT="/account";
    protected static final String ROOT_INVENTORY_ENDPOINT="/inventory";
    protected static final String ROOT_LISTING_ENDPOINT="/listings";
    protected static final String ROOT_LISTS_ENDPOINT="/lists";
    protected static final String ROOT_SALES_ENDPOINT="/sales";
    protected static final String ROOT_PURCHASES_ENDPOINT="/purchases";
    protected static final String ROOT_EXPANSION_ENDPOINT="/expansions";
    protected static final String ROOT_OPTIMIZER_ENDPOINT="/optimizer";
    protected static final String ROOT_CART_ENDPOINT="/cart";
    
    
   protected AbstractNexusService() {
	client = new RestClient(NexusConfig.getToken());
    }
    
    public void setCallListener(URLCallListener listener) {
		client.setCallListener(listener);
    }
   
   
   
   
}
