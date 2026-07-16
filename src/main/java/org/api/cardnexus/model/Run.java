package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumKindsRun;
import org.api.cardnexus.model.enums.EnumRunStatus;

public record Run (String id,EnumRunStatus status,Progress progress,RunResult result)
{
    	
}
record RunResult(String region,String currency,List<RunOption> options)
{
 
}

record RunOption(EnumKindsRun kind,int sellerCount,Amount total,Amount subtotal,Amount shipping,Amount buyerFee,List<UserOffer> sellers)
{
    
    
}