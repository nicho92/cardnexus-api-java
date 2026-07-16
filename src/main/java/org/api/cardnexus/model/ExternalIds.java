package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumFinishes;

public record ExternalIds (String scryfallId,String scryfallOracleId, List<ExtId> cardmarket,List<ExtId> tcgplayer)
{
     
}

record ExtId (EnumFinishes finish,String id) {}