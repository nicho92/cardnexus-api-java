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
    
    
    public static InventoryRequest create()
    {
	return new InventoryRequest();
    }
    
    
    public String getGame() {
        return game;
    }
    public InventoryRequest setGame(String game) {
        this.game = game;
        return this;
    }
    public List<Integer> getProductId() {
        return productId;
    }
    public InventoryRequest setProductIds(List<Integer> productId) {
        this.productId = productId;
        return this;
    }
    public EnumCondition getCondition() {
        return condition;
    }
    public InventoryRequest setCondition(EnumCondition condition) {
        this.condition = condition;
        return this;
    }
    public String getLanguage() {
        return language;
    }
    public InventoryRequest setLanguage(String language) {
        this.language = language;
        return this;
    }
    public EnumFinishes getFinish() {
        return finish;
    }
    public InventoryRequest setFinish(EnumFinishes finish) {
        this.finish = finish;
        return this;
    }
    public String getCommentContains() {
        return commentContains;
    }
    public InventoryRequest setCommentContains(String commentContains) {
        this.commentContains = commentContains;
        return this;
    }
    public Boolean getForSale() {
        return forSale;
    }
    public InventoryRequest setForSale(Boolean forSale) {
        this.forSale = forSale;
        return this;
    }
    public Boolean getGraded() {
        return graded;
    }
    public InventoryRequest setGraded(Boolean graded) {
        this.graded = graded;
        return this;
    }
    
    
    
    
    
    
}
