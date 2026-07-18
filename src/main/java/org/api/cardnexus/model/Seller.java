package org.api.cardnexus.model;

public record Seller (String id, String username, String type,String country,String currency,Boolean available, String urlProfilePage)
{
    
    public Seller(String id, String username, String type, String country, String currency, Boolean available,String urlProfilePage) {
	this.id = id;
	this.username = username;
	this.type = type;
	this.country = country;
	this.currency = currency;
	this.available = available;
	this.urlProfilePage = "https://cardnexus.com/fr/users/"+username+"/inventory";
	
    }
     
}
