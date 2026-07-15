package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumStatus;

public record NexusList (String id,String name,String game,EnumStatus status,String description,double completionPercentage, int itemCount,int totalQuantity,List<ListItem> items)
{
 
    @Override
    public String toString() {
       return id();
    }
    
    public boolean isComplete()
    {
	return completionPercentage()>=100;
    }

    
    
    
}
