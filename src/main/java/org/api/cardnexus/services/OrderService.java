package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.Order;
import org.api.cardnexus.model.Pagination;
import org.api.cardnexus.model.requests.SalesRequest;

public class OrderService extends AbstractNexusService {

    
    public List<Order> listOrders(SalesRequest req) throws IOException
    {
	var ret = new ArrayList<Order>();
	var pagination=new Pagination();
	while(pagination.hasMore())
	{
		var result =  client.getPaginated(ROOT_SALES_ENDPOINT+"?"+req.toQueryString(), null, Order.class);
		ret.addAll(result.getData());
		pagination = result.getPagination();
	}
	return ret;
	
    }
    
    
}
