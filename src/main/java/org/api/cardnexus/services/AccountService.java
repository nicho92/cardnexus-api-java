package org.api.cardnexus.services;

import java.io.IOException;
import java.net.URI;

import org.api.cardnexus.model.Account;
import org.api.cardnexus.model.Balance;
import org.api.cardnexus.model.ManagedAccount;
import org.api.cardnexus.model.OnBoardingLink;
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
    
    public OnBoardingLink createOnBoardingLink(String returnUrl) throws IOException
    {
	var obj = new JsonObject();
	obj.addProperty("returnUrl", returnUrl);
	
	return client.post(ROOT_ACCOUNT_ENDPOINT+"/onboarding-link",obj, OnBoardingLink.class);
    }
    
    public ManagedAccount createManagedAccount(String email, String username, String country) throws IOException
    {
	var obj = new JsonObject();
	obj.addProperty("email", email);
	obj.addProperty("username", username);
	obj.addProperty("country", country);
	
	return client.post(ROOT_ACCOUNT_ENDPOINT+"/onboarding-link",obj, ManagedAccount.class);
    }
    
    
}
    

