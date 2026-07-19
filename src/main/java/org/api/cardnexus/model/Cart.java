package org.api.cardnexus.model;

import java.util.List;

public record Cart(String deliveryCountry, int itemCount, List<CartItem> sellers) 
{

}
