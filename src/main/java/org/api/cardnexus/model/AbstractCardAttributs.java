package org.api.cardnexus.model;

import java.lang.reflect.Type;

import org.api.cardnexus.model.attributs.FabAttributs;
import org.api.cardnexus.model.attributs.LorcanaAttributs;
import org.api.cardnexus.model.attributs.MTGAttributs;
import org.api.cardnexus.model.attributs.NarutoAttributs;
import org.api.cardnexus.model.attributs.OnePieceAttributs;
import org.api.cardnexus.model.attributs.PokemonAttributs;

public abstract class AbstractCardAttributs {

    public static Type getAttributsByGame(String game) {
	
	switch(game)
	{
		case "mtg" : return MTGAttributs.class;
		case "onepiece": return OnePieceAttributs.class;
		case "fab" : return FabAttributs.class;
		case "lorcana" : return LorcanaAttributs.class;
		case "pokemon": return PokemonAttributs.class;
		case "naruto-mythos": return NarutoAttributs.class;
		default : return null;
	}
	
	
    }
     
}
