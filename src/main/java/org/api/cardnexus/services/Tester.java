package org.api.cardnexus.services;

import java.util.List;

import org.api.cardnexus.api.ListingsApi;
import org.api.cardnexus.client.ApiClient;
import org.api.cardnexus.client.ApiException;
import org.api.cardnexus.client.Configuration;
import org.api.cardnexus.client.auth.HttpBearerAuth;
import org.api.cardnexus.model.CardCondition;

public class Tester {

    	public static void main(String[] args) {
    	  ApiClient defaultClient = Configuration.getDefaultApiClient();
    	    defaultClient.setBasePath("https://public-api.cardnexus.com/v1");
    	    
    	    // Configure HTTP bearer authorization: bearerAuth
    	    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    	    bearerAuth.setBearerToken("<your token>");

    	    ListingsApi apiInstance = new ListingsApi(defaultClient);
    	    
    	    String cursor = "cursor_example"; // String | 
    	    Integer limit = 50; // Integer | 
    	    String game = "game_example"; // String | 
    	    List<Object> productId = null; // List<Object> | 
    	    CardCondition condition = CardCondition.fromValue("NM"); // CardCondition | 
    	    String language = "language_example"; // String | 
    	    String finish = "Standard"; // String | 
    	    Boolean graded = true; // Boolean | 
    	    String customId = "customId_example"; // String | 
    	    String customIdPrefix = "customIdPrefix_example"; // String | 
    	    String customIdContains = "customIdContains_example"; // String | 
    	    String commentContains = "commentContains_example"; // String | 
    	    try {
    	      var result = apiInstance.listingsList(cursor, limit, game, productId, condition, language, finish, graded, customId, customIdPrefix, customIdContains, commentContains);
    	      System.out.println(result);
    	    } catch (ApiException e) {
    	      System.err.println("Exception when calling ListingsApi#listingsList");
    	      System.err.println("Status code: " + e.getCode());
    	      System.err.println("Reason: " + e.getResponseBody());
    	      System.err.println("Response headers: " + e.getResponseHeaders());
    	      e.printStackTrace();
    	    }
    	  }
	}
    

