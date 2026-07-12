package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;

import org.api.cardnexus.model.enums.EnumOrderStatus;

public class Order {

    private String orderNumber;
    private EnumOrderStatus status;
    private Date placedAt;
    private Date  updatedAt;
    private Date shippedAt;
    private Date deliveredAt;
    private Date completedAt;
    private String currency;
    private List<OrderItem> items;
    private User buyer;
    
    private Amount subtotal;
    private Amount shippingAmount;
    
    public User getBuyer() {
	return buyer;
    }
    public void setBuyer(User buyer) {
	this.buyer = buyer;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public EnumOrderStatus getStatus() {
        return status;
    }
    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }
    public Date getPlacedAt() {
        return placedAt;
    }
    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Date getShippedAt() {
        return shippedAt;
    }
    public void setShippedAt(Date shippedAt) {
        this.shippedAt = shippedAt;
    }
    public Date getDeliveredAt() {
        return deliveredAt;
    }
    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
    public Date getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public Amount getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Amount subtotal) {
        this.subtotal = subtotal;
    }
    public Amount getShippingAmount() {
        return shippingAmount;
    }
    public void setShippingAmount(Amount shippingAmount) {
        this.shippingAmount = shippingAmount;
    }
    
    
    
    
    
    
}
