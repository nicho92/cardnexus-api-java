package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumUnmetReason;

public record Unmet(int targetIndex, int requested , int sourced,  EnumUnmetReason reason) {

}
