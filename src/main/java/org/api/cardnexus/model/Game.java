package org.api.cardnexus.model;

import java.util.Date;

public class Game {

    private String id;
    private String name;
    private Date releaseDate;
    private Integer cardCount;
    private Integer sealedProductCount;
    private String logoUrl;
    
    @Override
    public String toString() {
        return getName();
    }
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    
    
    
    
}
