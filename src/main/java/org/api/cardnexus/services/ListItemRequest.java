package org.api.cardnexus.services;

import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.model.ListItemRequestData;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class ListItemRequest {

    private List<ListItemRequestData> items;
    
    public ListItemRequest() {
	items = new ArrayList<>();
    }
    
    public void addItem(ListItemRequestData data)
    {
	items.add(data);
    }
    
    public void addItem(List<ListItemRequestData> datas)
    {
	items.addAll(datas);
    }
    
    
    public void addItem(String itemId, Integer productId,EnumFinishes finish,String language,int quantity,EnumCondition minCondition,double wantPrice, double sellPrice)
    {
	addItem(new ListItemRequestData(itemId, productId, finish, language, quantity, minCondition, wantPrice, sellPrice));
    }
    
    
    
    
}
