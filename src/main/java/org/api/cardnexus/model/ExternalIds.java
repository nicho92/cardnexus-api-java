package org.api.cardnexus.model;

import java.util.List;

public record ExternalIds (String scryfallId,String scryfallOracleId, List<ExtId> cardmarket,List<ExtId> tcgplayer)
{
     
}

