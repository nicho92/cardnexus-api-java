package org.api.cardnexus.model;

import java.util.List;

public record UserOffer (String sellerId, Amount itemsSubtotal, Amount shipping, Amount vat, String packageSize, List<UserOfferLine> lines)
{
    
}



