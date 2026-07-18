package org.api.cardnexus.model.requests;

import java.util.List;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class InventoryRequest extends AbstractGetRequest{
    private String game = NexusConfig.DEFAULT_GAME_VALUE;
    private List<Integer> productId;
    private EnumCondition condition;
    private String language;
    private EnumFinishes finish;
    private String commentContains;
    private Boolean forSale;
    private Boolean graded;
    
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public List<Integer> getProductId() {
        return productId;
    }
    public void setProductIds(List<Integer> productId) {
        this.productId = productId;
    }
    public EnumCondition getCondition() {
        return condition;
    }
    public void setCondition(EnumCondition condition) {
        this.condition = condition;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public void setFinish(EnumFinishes finish) {
        this.finish = finish;
    }
    public String getCommentContains() {
        return commentContains;
    }
    public void setCommentContains(String commentContains) {
        this.commentContains = commentContains;
    }
    public Boolean getForSale() {
        return forSale;
    }
    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }
    public Boolean getGraded() {
        return graded;
    }
    public void setGraded(Boolean graded) {
        this.graded = graded;
    }
    
    
    
    
    
    
}
