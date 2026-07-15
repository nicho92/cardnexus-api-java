package org.api.cardnexus.model;

import java.util.Map;

import org.api.cardnexus.model.enums.EnumFinishes;

public record Price (Integer productId,Map<EnumFinishes, ProductPriceMarket> pricesByFinish) {}
