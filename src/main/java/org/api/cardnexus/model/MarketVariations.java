package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.tools.Formatter;


public record MarketVariations (Double low,  Double mid, Double marketValue, Double change24h,  Double change7d, Double change30d,  Date date)
{
    
    @Override
    public String toString() {
	return Formatter.format(marketValue());
    }
     
}
