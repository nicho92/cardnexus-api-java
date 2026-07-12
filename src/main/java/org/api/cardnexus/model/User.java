package org.api.cardnexus.model;

import java.util.Map;

public class User {

    String id;
    String username;
    String avatarUrl;
    String country;
    String type;
    Map<String,Double> rating;
    
    
    @Override
    public String toString() {
          return getUsername();
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Map<String, Double> getRating() {
        return rating;
    }
    public void setRating(Map<String, Double> rating) {
        this.rating = rating;
    }
    
    
}
