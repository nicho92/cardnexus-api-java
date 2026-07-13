package org.api.cardnexus.model.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.api.cardnexus.configuration.NexusConstants;
import org.api.cardnexus.model.enums.EnumProductSort;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.enums.EnumSortDirection;

public class SearchProductRequest  {
    
    private int offset;
    private int limit;
    private List<Integer> productIds;
    private Integer expansionId;
    private String name;
    private String nameSlug;
    private List<Integer> cardmarketId;
    private List<Integer> tcgplayerId;
    private EnumProductSort sortBy;
    private EnumSortDirection sortDirection;
    private Map<String, Object> gameFilters;
    
    
    public SearchProductRequest() {
	limit = NexusConstants.LIMIT_LIST_RESULTS;
	gameFilters = new HashMap<>();
	
    }
    
    public void setProductTypes(EnumProductType...values)
    {
	var productTypes = new HashMap<>();
	productTypes.put("op", "or");
	productTypes.put("values", values);
    }
    
    public void addFilter(String name, String value)
    {
	gameFilters.put(name, value);
    }
    
    
    public void setGame(String gameId)
    {
	gameFilters.put("game", gameId);
    }
    
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public List<Integer> getProductIds() {
        return productIds;
    }
    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
    public Integer getExpansionId() {
        return expansionId;
    }
    public void setExpansionId(Integer expansionId) {
        this.expansionId = expansionId;
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
    public List<Integer> getCardmarketId() {
        return cardmarketId;
    }
    public void setCardmarketId(List<Integer> cardmarketId) {
        this.cardmarketId = cardmarketId;
    }
    public List<Integer> getTcgplayerId() {
        return tcgplayerId;
    }
    public void setTcgplayerId(List<Integer> tcgplayerId) {
        this.tcgplayerId = tcgplayerId;
    }

    public EnumProductSort getSortBy() {
        return sortBy;
    }

    public void setSortBy(EnumProductSort sortBy) {
        this.sortBy = sortBy;
    }

    public EnumSortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(EnumSortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }
   
    
    
  }
