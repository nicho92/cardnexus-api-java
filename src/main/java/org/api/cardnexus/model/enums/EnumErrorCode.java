package org.api.cardnexus.model.enums;

public enum EnumErrorCode {

    
    PRODUCT_NOT_FOUND("No catalogue product with this id"),
    INVALID_LANGUAGE("The product doesn't exist in this language."),
    INVALID_FINISH("The product doesn't exist in this finish."),
    INVALID_QUANTITY("Quantity must be a whole number of 1 or more."),
    CONDITION_REQUIRED("Raw cards need a condition."),
    INVALID_IDENTITY("A line takes either condition (raw cards) or graded (graded cards), not both."),
    CUSTOM_ID_CONFLICT("Another of your live lines, or another line in this request, already uses this customId."),
    LOCATION_NOT_FOUND("The location name matches none of your locations."),
    TAG_NOT_FOUND("The tag name matches none of your tags.");

    private String label;

    EnumErrorCode(String label) {
	this.label=label;
    }
    
    public String getLabel() {
	return label;
    }
}
