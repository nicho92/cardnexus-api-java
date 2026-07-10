package org.api.cardnexus.services;

import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.api.ProductsApi;
import org.api.cardnexus.client.JSON;
import org.api.cardnexus.model.CardProductDetail;
import org.api.cardnexus.model.ExpansionSummary;
import org.api.cardnexus.model.GameSummary;
import org.api.cardnexus.model.ProductsSearchProducts200Response;
import org.api.cardnexus.model.ProductsSearchProductsRequest;
import org.api.cardnexus.model.ProductsSearchProductsRequest.SortByEnum;
import org.api.cardnexus.model.ProductsSearchProductsRequest.SortDirectionEnum;
import org.api.cardnexus.model.SealedProductDetail;
import org.api.cardnexus.services.interfaces.AbstractNexusService;

public class ProductsServices extends AbstractNexusService {

	private ProductsApi apiInstance;

	public ProductsServices() {
		apiInstance = new ProductsApi(defaultClient);
	}
	
	
	public GameSummary getGameById(String id)
	{
		return apiInstance.productsGetGame(id);
	}
	
	public List<GameSummary> listGames()
	{
		return apiInstance.productsListGames().getData();
	}
	
	
	public List<ExpansionSummary> listExpansions(GameSummary g)
	{
		return listExpansions(g.getId());
	}
	
	
	public List<ExpansionSummary> listExpansions(String gameId)
	{
		
		var ret = new ArrayList<ExpansionSummary>();
		var results = apiInstance.productsListGameExpansions(gameId, 0, 200);
		ret.addAll(results.getData());
		
		while(results.getPagination().getHasMore())
		{
			ret.addAll(results.getData());
			results = apiInstance.productsListGameExpansions(gameId, ret.size(), null);
		}
		return ret;
	
	}
		
	public CardProductDetail getCardProduct(String idProduct)
	{
		var obj = JSON.getGson().toJsonTree(apiInstance.productsGetProduct(idProduct)).getAsJsonObject();
		return JSON.getGson().fromJson(obj, CardProductDetail.class);
	}
	
	public SealedProductDetail getSealedProduct(String idProduct)
	{
	    var obj = JSON.getGson().toJsonTree(apiInstance.productsGetProduct(idProduct)).getAsJsonObject();
	    return JSON.getGson().fromJson(obj, SealedProductDetail.class);
	}
	
	public ProductsSearchProducts200Response searchProduct(String name)
	{
	    
	    var req = new ProductsSearchProductsRequest(); 
	    req.setName(name);
	    req.setLimit(200);
	    req.setOffset(0);
	    req.setSortBy(SortByEnum.NAME);
	    req.setSortDirection(SortDirectionEnum.ASC);
	    
	    
	   return  apiInstance.productsSearchProducts(req);
	    
	    
	}
	
	
	
	
	
}
