package org.api.cardnexus.model.account;

import java.util.Date;

public class Account {

    private String username;
    private String email;
    private String imageUrl;
    private Date createdAt;
    private Seller seller;
    
    @Override
    public String toString() {
          return getUsername();
    }
    
    public Seller getSeller() {
	return seller;
    }
    public void setSeller(Seller seller) {
	this.seller = seller;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    
    
    
    
}
