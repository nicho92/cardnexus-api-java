package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record MarketList(String listingId,Amount price, Integer quantity, EnumCondition condition, String language, EnumFinishes finish, Graded graded, String comment, Seller seller, Amount shipping) {

}
