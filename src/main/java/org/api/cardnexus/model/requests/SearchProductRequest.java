package org.api.cardnexus.model.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumProductSort;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.enums.EnumSearchMod;
import org.api.cardnexus.model.enums.EnumSortDirection;

public class SearchProductRequest
{
    
    private int offset;
    private int limit = NexusConfig.getLimitListResults();
    private List<Integer> productIds;
    private List<Integer> expansionId;
    private String name;
    private String nameSlug;
    private String printNumber;
    private List<Integer> cardmarketId;
    private List<Integer> tcgplayerId;
    private EnumProductSort sortBy;
    private EnumSortDirection sortDirection;
    private Map<String, Object> gameFilters;
    private EnumSearchMod nameSearchMod;
    private HashMap<String,Object> productType;
    private ListingProductRequestRecord listings;
    
    private SearchProductRequest() {
	
	gameFilters = new HashMap<>();
	setGame(NexusConfig.getDefaultGameValue());
	setListings(null, false, null);
    }
    
    public static SearchProductRequest create()
    {
	return new SearchProductRequest();
    }
    
    
    public SearchProductRequest setProductTypes(EnumProductType...values)
    {
	productType = new HashMap<>();
	
	if(values.length==1)
	    productType.put("op", "and");
	else
	    productType.put("op", "or");
	
	productType.put("values", values);
	return this;
    }
    
    public SearchProductRequest addFilter(String name, String value)
    {
	gameFilters.put(name, value);
	return this;
    }
        
    public SearchProductRequest strict() {
	nameSearchMod = EnumSearchMod.STRICT;
	return this;
    }
    
    public SearchProductRequest contains() {
	this.nameSearchMod = EnumSearchMod.CONTAINS;
	return this;
    }
    
    
    public EnumSearchMod getNameSearchMod() {
	return nameSearchMod;
    }
    
    public SearchProductRequest setListings(String deliveryCountry, Boolean inStock, EnumCondition conditions) {
	this.listings = new ListingProductRequestRecord(deliveryCountry, inStock, conditions==null?List.of(EnumCondition.values()):List.of(conditions));
	return this;
    }
    
    public SearchProductRequest setGame(String gameId)
    {
	gameFilters.put("game", gameId);
	return this;
    }
    public SearchProductRequest setLimit(int limit) {
        this.limit = limit;
        return this;
    }
    public SearchProductRequest setPrintNumber(String printNumber) {
	this.printNumber = printNumber;
	 return this;
    }
    public SearchProductRequest setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
        return this;
    }
    public SearchProductRequest setExpansionId(List<Integer> expansionId) {
        this.expansionId = expansionId;
        return this;
    }
    
    public SearchProductRequest setExpansionId(Integer expansionId) {
        this.expansionId = List.of(expansionId);
        return this;
    }
    
    public SearchProductRequest setName(String name) {
        this.name = name;
        return this;
    }
    public SearchProductRequest setNameSlug(String nameSlug) {
        this.nameSlug = nameSlug;
        return this;
    }
    public SearchProductRequest setCardmarketId(List<Integer> cardmarketId) {
        this.cardmarketId = cardmarketId;
        return this;
    }
    public SearchProductRequest setTcgplayerId(List<Integer> tcgplayerId) {
        this.tcgplayerId = tcgplayerId;
        return this;
    }
    public SearchProductRequest setSortBy(EnumProductSort sortBy) {
        this.sortBy = sortBy;
        return this;
    }
    public SearchProductRequest setSortDirection(EnumSortDirection sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    
    
    
    public Map<String, Object> getGameFilters() {
        return gameFilters;
    }

    public void setGameFilters(Map<String, Object> gameFilters) {
        this.gameFilters = gameFilters;
    }

    public int getLimit() {
        return limit;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public List<Integer> getExpansionId() {
        return expansionId;
    }

    public String getName() {
        return name;
    }

    public String getNameSlug() {
        return nameSlug;
    }

    public List<Integer> getCardmarketId() {
        return cardmarketId;
    }

    public List<Integer> getTcgplayerId() {
        return tcgplayerId;
    }

    public EnumProductSort getSortBy() {
        return sortBy;
    }

    public EnumSortDirection getSortDirection() {
        return sortDirection;
    }

    public SearchProductRequest removeListing() {
	listings=null;
	return this;
    }
 }
record ListingProductRequestRecord(String deliveryCountry, Boolean inStock, List<EnumCondition> condition) {

}


