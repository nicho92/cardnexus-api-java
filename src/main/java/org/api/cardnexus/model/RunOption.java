package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumKindsRun;

public record RunOption(EnumKindsRun kind,int sellerCount,Amount total,Amount subtotal,Amount shipping,Amount buyerFee)
{
    
    
}
