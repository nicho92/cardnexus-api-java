package org.api.cardnexus.model;

import java.util.List;
import java.util.Map;

import org.api.cardnexus.model.enums.EnumErrorCode;

public record AddInventoryResults(List<InventoryLine> created, List<ResultsError> errors) 
{

    public boolean hasErrors()
    {
	return !errors.isEmpty();
    }
    
    
}
record ResultsError(int index, EnumErrorCode code, Map<String,Object> data)
{
    
}