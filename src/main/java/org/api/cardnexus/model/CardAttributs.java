package org.api.cardnexus.model;

import java.util.List;

public record CardAttributs (List<String> color,List<String> colorIdentity, List<String> types,String description) {}
   