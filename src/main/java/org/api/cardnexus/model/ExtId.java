package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumFinishes;

public record ExtId (EnumFinishes finish,String id) {}