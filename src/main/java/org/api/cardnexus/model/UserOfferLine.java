package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record UserOfferLine(int product, String listingId, int quantity, Amount unitPrice, EnumFinishes finish,EnumCondition condition, String language) {}