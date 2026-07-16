package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record UserOffer (String sellerId, Amount itemsSubtotal, Amount shipping, Amount vat, String packageSize, List<UserOfferLine> lines)
{
    
}


record UserOfferLine(Integer product, String listingId, int quantity, Amount unitPrice, EnumFinishes finish,EnumCondition condition, String language) {}
