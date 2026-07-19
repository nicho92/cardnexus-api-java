package org.api.cardnexus.model.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.MinMax;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumOperand;
import org.api.cardnexus.model.enums.EnumProductSort;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.enums.EnumSortDirection;

public class SearchInventoryRequest {
    
    private int offset=0;
    private int limit = NexusConfig.LIMIT_LIST_RESULTS;
    
   
    private String name;
    private String customId;
    private String customIdPrefix;
    private String customIdContains;
    private String commentContains;
    private List<Integer> productIds;
    private Integer expansionId;
    private Boolean graded;
    private Boolean forSale;
    
    private Map<String, Object> gameFilters; 
    private Map<String,Object>  tags;
    private Map<String,Object> location;
    private Map<String,Object> condition;
    private Map<String,Object>language;
    private Map<String,Object> finish;
    private Map<String,Object> productType;
    
    private MinMax<Integer> quantity;
    private MinMax<Double> listingPrice;
    
    private EnumProductSort sortBy;
    private EnumSortDirection sortDirection;

    private SearchInventoryRequest() {
	gameFilters=new HashMap<>();
	
	if(NexusConfig.DEFAULT_GAME_VALUE!=null)
	    setGame(NexusConfig.DEFAULT_GAME_VALUE);
    }
    
    public static SearchInventoryRequest create()
    {
	return new SearchInventoryRequest();
    }
    
    public SearchInventoryRequest setTags(EnumOperand op, List<String> tag)
    {
	tags = new HashMap<>();
	tags.put("op", op.name());
	
	if(tag==null)
	    tags.put("values", null);
	else
	    tags.put("values", tag);
	
	return this;
    }
        
    public SearchInventoryRequest setLocation(EnumOperand op, List<String> loc)
    {
	location = new HashMap<>();
	location.put("op", op.name());
	location.put("values", loc);
	
	return this;
    }
    
    public SearchInventoryRequest setCondition(EnumOperand op, List<EnumCondition> conds)
    {
	condition = new HashMap<>();
	condition.put("op", op.name());
	condition.put("values", conds);
	
	return this;
    }
    
    public SearchInventoryRequest setLanguage(EnumOperand op, List<String> langs)
    {
	language = new HashMap<>();
	language.put("op", op.name());
	language.put("values", langs);
	
	return this;
    }
            
    public SearchInventoryRequest setFinish(EnumOperand op, List<EnumFinishes> finishes)
    {
	finish = new HashMap<>();
	finish.put("op", op.name());
	finish.put("values", finishes);
	return this;
    }
    
    public SearchInventoryRequest setProductType(EnumOperand op, List<EnumProductType> t)
    {
	productType = new HashMap<>();
	productType.put("op", op.name());
	productType.put("values", t);
	return this;
    }
    
    public SearchInventoryRequest setQuantity(Integer min, Integer max)
    {
	this.quantity = new MinMax<Integer>(min,max);
	return this;
    }
    
    public SearchInventoryRequest setListingPrice(Double min, Double  max)
    {
	this.listingPrice = new MinMax<Double>(min,max);
	return this;
    }
    
    public SearchInventoryRequest setGame(String gameId)
    {
	gameFilters.put("game", gameId);
	return this;
    }
    
    public SearchInventoryRequest setName(String name) {
	this.name = name;
	return this;
    }
    public SearchInventoryRequest setCustomId(String customId) {
	this.customId = customId;
	return this;
    }
    public SearchInventoryRequest setCustomIdContains(String customIdContains) {
	this.customIdContains = customIdContains;
	return this;
    }
    public SearchInventoryRequest setCommentContains(String commentContains) {
	this.commentContains = commentContains;
	return this;
    }
    public SearchInventoryRequest setProductIds(List<Integer> productIds) {
	this.productIds = productIds;
	return this;
    }
    public SearchInventoryRequest setExpansionId(int expansionId) {
	this.expansionId = expansionId;
	return this;
    }
    public SearchInventoryRequest setGraded(boolean graded) {
	this.graded = graded;
	return this;
    }
    public SearchInventoryRequest setForSale(boolean forSale) {
	this.forSale = forSale;
	return this;
    }
    public SearchInventoryRequest setCustomIdPrefix(String customIdPrefix) {
	this.customIdPrefix = customIdPrefix;
	return this;
    }
    
    public SearchInventoryRequest setSortBy(EnumProductSort sortBy) {
	this.sortBy = sortBy;
	return this;
    }
    public SearchInventoryRequest setSortDirection(EnumSortDirection sortDirection) {
	this.sortDirection = sortDirection;
	return this;
    }
    
    public void setOffset(int offset) {
	this.offset = offset;
    }
    
}
