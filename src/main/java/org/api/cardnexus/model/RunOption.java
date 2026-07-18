package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumKindsRun;

record RunOption(EnumKindsRun kind,int sellerCount,Amount total,Amount subtotal,Amount shipping,Amount buyerFee,List<UserOffer> sellers)
{
    
    
}