package org.api.cardnexus.services.catalog;

import java.io.IOException;

import org.api.cardnexus.model.Account;
import org.api.cardnexus.model.Balance;
import org.api.cardnexus.model.Vacation;
import org.api.cardnexus.services.AbstractNexusService;

import com.google.gson.JsonObject;

public class AccountService extends AbstractNexusService {
	
    	
    
    public Account getAccount() throws IOException
    {
	return client.get(ROOT_ACCOUNT_ENDPOINT+"/me", null, Account.class);
    }
    
    public Balance getWalletBalance() throws IOException
    {
	return client.get(ROOT_ACCOUNT_ENDPOINT+"/balance", null, Balance.class);
    }
    
    public Vacation getVacationMode() throws IOException
    {
	return client.get(ROOT_ACCOUNT_ENDPOINT+"/vacation", null, Vacation.class);
    }
    
    public Vacation setVacationMode(boolean enabled, String reason) throws IOException
    {
	var obj = new JsonObject();
	      obj.addProperty("enabled", enabled);
	      obj.addProperty("reason", reason);
	      
	      
	return client.post(ROOT_ACCOUNT_ENDPOINT+"/vacation", obj, null,Vacation.class);
    }
    
    
    
    
}
    

