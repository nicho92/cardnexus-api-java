package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;

import org.api.cardnexus.model.enums.EnumOrderStatus;

public record Order (String orderNumber,EnumOrderStatus status,Date placedAt,Date  updatedAt, Date shippedAt,Date deliveredAt,Date completedAt,String currency,List<OrderItem> items,User buyer,Payout payout,Fee sellerFee,Amount subtotal,Amount shippingAmount)
{
}
 