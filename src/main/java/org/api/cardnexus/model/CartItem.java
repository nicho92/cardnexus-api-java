package org.api.cardnexus.model;

import java.util.List;

public record CartItem(Seller seller, Amount itemsSubtotal, List<CartItemEntry> items) {

}
