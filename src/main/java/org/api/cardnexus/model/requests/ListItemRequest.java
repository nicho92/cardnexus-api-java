package org.api.cardnexus.model.requests;

import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class ListItemRequest {

    private List<ListItemRequestData> items;
    
    public static ListItemRequest create()
    {
	return new ListItemRequest();
    }
    
    
    private ListItemRequest() {
	items = new ArrayList<>();
    }
    
    public ListItemRequest addItem(ListItemRequestData data)
    {
	items.add(data);
	return this;
    }
    
    public ListItemRequest addItem(List<ListItemRequestData> datas)
    {
	items.addAll(datas);
	return this;
    }
    
    
    public ListItemRequest addItem(String itemId, Integer productId,EnumFinishes finish,String language,int quantity,EnumCondition minCondition,double wantPrice, double sellPrice)
    {
	addItem(new ListItemRequestData(itemId, productId, finish, language, quantity, minCondition, wantPrice, sellPrice));
	return this;
    }
}

record ListItemRequestData(String itemId, Integer productId, EnumFinishes finish, String language, int quantity,EnumCondition minCondition, double wantPrice, double sellPrice) {   }


