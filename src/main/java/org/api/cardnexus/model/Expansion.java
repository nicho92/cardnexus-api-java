package org.api.cardnexus.model;

import java.util.Date;
import java.util.List;

public class Expansion {

    private Integer id;
    private String gameId;
    private String name;
    private String code;
    private Date releaseDate;
    private Integer cardCount;
    private Integer sealedProductCount;
    private List<String> languages;
    private String logoUrl;
    private String symbolUrl;
    
    
    @Override
    public String toString() {
          return getName();
    }
    
    
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Integer getCardCount() {
        return cardCount;
    }
    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }
    public Integer getSealedProductCount() {
        return sealedProductCount;
    }
    public void setSealedProductCount(Integer sealedProductCount) {
        this.sealedProductCount = sealedProductCount;
    }
    public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    public String getSymbolUrl() {
        return symbolUrl;
    }
    public void setSymbolUrl(String symbolUrl) {
        this.symbolUrl = symbolUrl;
    }
        
    
}
