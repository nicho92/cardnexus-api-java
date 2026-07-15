package org.api.cardnexus.model;

import java.util.Date;

public record CardNexusPrice (Amount low,Integer listingCount,Integer availableQuantity,Date date,Regions regions)
{
}
