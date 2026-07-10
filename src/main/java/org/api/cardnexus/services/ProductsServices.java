package org.api.cardnexus.services;

import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.api.ProductsApi;
import org.api.cardnexus.model.ExpansionSummary;
import org.api.cardnexus.model.GameSummary;
import org.api.cardnexus.services.interfaces.AbstractNexusService;
import org.api.cardnexus.services.model.NexusProduct;

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
		
	public NexusProduct getProduct(String idProduct)
	{
		var obj = apiInstance.productsGetProduct(idProduct);
		
		var prod = new NexusProduct();
		
		return prod;
	}
	
	
	
	
	
}
