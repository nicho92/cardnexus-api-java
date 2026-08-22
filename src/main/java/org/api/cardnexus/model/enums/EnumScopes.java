package org.api.cardnexus.model.enums;

import com.google.gson.annotations.SerializedName;

public enum EnumScopes {
    
    @SerializedName(value="*") FULL,
    @SerializedName(value="inventory:read") INVENTORY_READ, 
    @SerializedName(value="inventory:write") INVENTORY_WRITE,
    
    @SerializedName(value="listings:read") LISTINGS_READ,
    @SerializedName(value="listings:write") LISTINGS_WRITE,

    @SerializedName(value="lists:read") LISTS_READ,
    @SerializedName(value="lists:write") LISTS_WRITE,

    @SerializedName(value="cart:read") CART_READ,
    @SerializedName(value="cart:write") CART_WRITE,

    @SerializedName(value="sales:read") SALES_READ,
    @SerializedName(value="sales:write") SALES_WRITE,

    @SerializedName(value="purchases:read") PURCHASES_READ,
    @SerializedName(value="purchases:write") PURCHASES_WRITE,

    @SerializedName(value="disputes:read") DISPUTES_READ,
    @SerializedName(value="disputes:write") DISPUTES_WRITE,

    @SerializedName(value="messaging:read") MESSAGING_READ,
    @SerializedName(value="messaging:write") MESSAGING_WRITE,

    @SerializedName(value="account:read") ACCOUNT_READ,
    @SerializedName(value="account:write") ACCOUNT_WRITE,

    @SerializedName(value="financial:read") FINANCIAL_READ,
    
    
}
