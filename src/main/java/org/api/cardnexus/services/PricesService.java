package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.History;
import org.api.cardnexus.model.Price;
import org.api.cardnexus.model.Sales;
import org.api.cardnexus.model.requests.HistoryRequest;

public class PricesService extends AbstractNexusService {

    public  Price getCurrentPrice(Integer idProduct) throws IOException
    {
	return client.get(ROOT_PRODUCT_ENDPOINT+"/"+idProduct+"/prices", null, Price.class);
    }
    
    
    public List<History> getHistoryPrice(HistoryRequest req) throws IOException
    {
	return client.getPaginated(ROOT_PRODUCT_ENDPOINT+"/"+req.getIdProduct()+"/prices/history?"+req.toQueryString(), null, History.class).getData();
	    
    }
    
    
    public List<Sales> getLastSales(AbstractProduct product) throws IOException
    {
	return getLastSales(product.getId());
    }
    
    public List<Sales> getLastSales(Integer productId) throws IOException
    {
	var ret = new ArrayList<Sales>();
	var result = client.getPaginated(ROOT_PRODUCT_ENDPOINT+"/"+productId+"/sales" ,null,Sales.class);
	    ret.addAll(result.getData());
	return ret;
    }
    
    
    
    
    
    
    
}
