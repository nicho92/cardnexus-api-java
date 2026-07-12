package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketCurrency;
import org.api.cardnexus.model.enums.EnumProductType;

public abstract class AbstractProduct {

    private Integer id;
    private String gameId;
    private Game game;
    private Expansion expansion;
    private String name;
    private String nameSlug;
    private List<String> languages;
    private String imageUrl;
    private EnumProductType productType;
    
    
    @Override
    public String toString() {
           return String.valueOf(getId());
    }
    
    public abstract MarketVariations getPrices(EnumFinishes finish, EnumMarketCurrency currency);
    
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Expansion getExpansion() {
        return expansion;
    }
    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNameSlug() {
        return nameSlug;
    }
    public void setNameSlug(String nameSlug) {
        this.nameSlug = nameSlug;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public EnumProductType getProductType() {
        return productType;
    }
    public void setProductType(EnumProductType productType) {
        this.productType = productType;
    }
    
    
    
    
    
    
    
    
    
    
    
}
