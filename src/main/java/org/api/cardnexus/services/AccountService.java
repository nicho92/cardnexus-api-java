package org.api.cardnexus.services;

import java.io.IOException;

import org.api.cardnexus.model.account.Account;
import org.api.cardnexus.model.account.Balance;
import org.api.cardnexus.model.account.Vacation;
import org.api.cardnexus.services.interfaces.AbstractNexusService;

import com.google.gson.JsonObject;

public class AccountService extends AbstractNexusService {
	
    	
    
    public Account getAccount() throws IOException
    {
	return client.get("/account/me", null, Account.class);
    }
    
    public Balance getWalletBalance() throws IOException
    {
	return client.get("/account/balance", null, Balance.class);
    }
    
    public Vacation getVacationMode() throws IOException
    {
	return client.get("/account/vacation", null, Vacation.class);
    }
    
    public Vacation setVacationMode(boolean enabled, String reason) throws IOException
    {
	var obj = new JsonObject();
	      obj.addProperty("enabled", enabled);
	      obj.addProperty("reason", reason);
	      
	      
	return client.post("/account/vacation", obj, null,Vacation.class);
    }
    
    
    
    
}
    

