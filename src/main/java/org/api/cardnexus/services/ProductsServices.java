package org.api.cardnexus.services;

import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.api.ProductsApi;
import org.api.cardnexus.model.ExpansionSummary;
import org.api.cardnexus.model.GameSummary;
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
	
	public List<ExpansionSummary> listExpansions(String gameId)
	{
		
		var ret = new ArrayList<ExpansionSummary>();
		
		var results = apiInstance.productsListGameExpansions(gameId, null, 200);
		
		ret.addAll(results.getData());
//		
//		while(results.getPagination().getHasMore())
//		{
//			System.out.println(results.getPagination());
//			results = apiInstance.productsListGameExpansions(gameId, results.getPagination().getOffset(), null);
//			ret.addAll(results.getData());
//		}
		
		return ret;
	
	}
	
}
