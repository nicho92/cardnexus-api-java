package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumStatus;

public record NexusList (String id,String name,String game,EnumStatus status,String description,double completionPercentage, int itemCount,int totalQuantity,boolean isPublic, String currency, EnumCondition  defaultMinCondition,String defaultLanguage,Date createdAt, Date updatedAt, List<ListItem> items)
{
    public boolean isComplete()
    {
	return completionPercentage()>=100;
    }
    
    @Override
    public final boolean equals(Object obj) {
        if(obj instanceof NexusList l)
            return l.id.equals(id());
        
        return false;
    }
    
    
    
    
    
}
