package org.api.cardnexus.model;

public record Availability(Boolean inStock, Integer listingCount, MarketList cheapest) {

}
