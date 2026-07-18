package org.api.cardnexus.model;

import java.util.Map;

import org.api.cardnexus.model.enums.EnumCondition;

record RegionData (Double low, Integer listingCount,Integer availableQuantity,String currency,Map<EnumCondition, LangageData> byCondition)
{

}