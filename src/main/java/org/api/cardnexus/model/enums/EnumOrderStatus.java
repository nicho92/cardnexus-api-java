package org.api.cardnexus.model.enums;

public enum EnumOrderStatus {
    pending_shipment,
    cancellation_requested,
    cancelled_buyer,
    cancelled_seller,
    cancelled_auto,
    shipped,
    delivered,
    completed,
    dispute_open,
    dispute_escalated,
    resolved_full_refund,
    resolved_partial_refund,
    resolved_no_refund,
    resolved_return,
    resolved_goodwill_refund;

}
