package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.Order;
import org.api.cardnexus.model.Pagination;
import org.api.cardnexus.model.enums.EnumCancelReasons;
import org.api.cardnexus.model.requests.SalesRequest;

import com.google.gson.JsonObject;

public class OrdersService extends AbstractNexusService {

    
    public List<Order> listOrders(SalesRequest req) throws IOException
    {
	var ret = new ArrayList<Order>();
	var pagination=new Pagination(null,null,null,true,null);
	while(pagination.hasMore())
	{
		var result =  client.getPaginated(ROOT_SALES_ENDPOINT+"?"+req.toQueryString(), Order.class);
		ret.addAll(result.data());
		pagination = result.pagination();
	}
	return ret;
	
    }
    
    public Order getOrder(String orderNumber) throws IOException
    {
	 return client.get(ROOT_SALES_ENDPOINT+"/"+orderNumber,Order.class);
    }
    
    public Order markAsShipped(String orderNumber, String trackNumber) throws IOException
    {
	var obj = new JsonObject();
		obj.addProperty("trackingNumber", trackNumber);
	
	 return client.post(ROOT_SALES_ENDPOINT+"/"+orderNumber+"/mark-shipped",obj,Order.class);
    }
    
    public Order markAsCanceled(String orderNumber, EnumCancelReasons reason, String comment) throws IOException
    {
	var obj = new JsonObject();
		obj.addProperty("reason", reason.name());
		obj.addProperty("reasonText", comment)	;
	
	 return client.post(ROOT_SALES_ENDPOINT+"/"+orderNumber+"/cancel",obj,Order.class);
    }
    
    
    public Order getPurchase(String orderNumber) throws IOException
    {
	 return client.get(ROOT_PURCHASES_ENDPOINT+"/"+orderNumber,Order.class);
    }
    
    public List<Order> listPurchases(SalesRequest req) throws IOException
    {
	var ret = new ArrayList<Order>();
	var pagination=new Pagination(null,null,null,true,null);
	while(pagination.hasMore())
	{
		var result =  client.getPaginated(ROOT_PURCHASES_ENDPOINT+"?"+req.toQueryString(), Order.class);
		ret.addAll(result.data());
		pagination = result.pagination();
	}
	return ret;
	
    }
    
    
    
}
