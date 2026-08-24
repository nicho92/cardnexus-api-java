package org.api.cardnexus.model.attributs;

import java.util.List;

import org.api.cardnexus.model.AbstractCardAttributs;

public class PokemonAttributs extends AbstractCardAttributs{

    private String type;
    private List<String> pokemonTypes;
    private int hp;
    private String stage;
    private String cardText;
    
    
    public String getType() {
        return type;
    }
    public List<String> getPokemonTypes() {
        return pokemonTypes;
    }
    public int getHp() {
        return hp;
    }
    public String getStage() {
        return stage;
    }
    public String getCardText() {
        return cardText;
    }
    
    
    
}
