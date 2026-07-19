package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public record CartItemEntry(String listingId, int productId,String productName, String imageUrl, EnumCondition condition, String language, EnumFinishes finish, Graded graded, int quantity, Amount unitPrice, Amount lineTotal, String addedAt) {

}
