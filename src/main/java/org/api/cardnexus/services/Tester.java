package org.api.cardnexus.services;

import java.time.LocalDate;

import org.api.cardnexus.api.PricingApi;
import org.api.cardnexus.client.ApiException;
import org.api.cardnexus.client.Configuration;
import org.api.cardnexus.client.auth.HttpBearerAuth;

public class Tester {

    	public static void main(String[] args) {
    	    var defaultClient = Configuration.getDefaultApiClient();
    	    defaultClient.setBasePath("https://public-api.cardnexus.com/v1");
    	    ((HttpBearerAuth) defaultClient.getAuthentication("bearerAuth")).setBearerToken("your token");

    	var apiInstance = new PricingApi(defaultClient);
        Object productId = 50212; 
        String marketplace = "cardmarket";  
        String finish = "Standard";  
        var from = LocalDate.of(2026,6,1);  
        var to = LocalDate.now();  
        try {
          var result = apiInstance.pricingGetHistory(productId, marketplace, finish, from, to);
          System.out.println(result);
        } catch (ApiException e) {
          System.err.println("Exception when calling PricingApi#pricingGetHistory");
          System.err.println("Status code: " + e.getCode());
          System.err.println("Reason: " + e.getResponseBody());
          System.err.println("Response headers: " + e.getResponseHeaders());
          e.printStackTrace();
        }
      }

}
    

