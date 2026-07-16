package org.api.cardnexus.services;

import java.io.IOException;

import org.api.cardnexus.model.Account;
import org.api.cardnexus.model.Balance;
import org.api.cardnexus.model.Vacation;

import com.google.gson.JsonObject;

public class AccountService extends AbstractNexusService {
	
    	
    
    public Account getAccount() throws IOException
    {
	return client.get(ROOT_ACCOUNT_ENDPOINT+"/me", Account.class);
    }
    
    public Balance getWalletBalance() throws IOException
    {
	return client.get(ROOT_ACCOUNT_ENDPOINT+"/balance", Balance.class);
    }
    
    public Vacation getVacationMode() throws IOException
    {
	return client.get(ROOT_ACCOUNT_ENDPOINT+"/vacation", Vacation.class);
    }
    
    public Vacation setVacationMode(boolean enabled, String reason) throws IOException
    {
	var obj = new JsonObject();
	      obj.addProperty("enabled", enabled);
	      obj.addProperty("reason", reason);
	      
	      
	return client.post(ROOT_ACCOUNT_ENDPOINT+"/vacation", obj, Vacation.class);
    }
    
    
    
    
}
    

