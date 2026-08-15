package org.api.cardnexus.model;

import java.util.List;

public record UserOffer (Seller seller, Amount itemsSubtotal, Amount shipping, Amount vat, String packageSize, List<UserOfferLine> items)
{
    
}



