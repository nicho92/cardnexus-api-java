package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumFinishes;

public class ExtId {

    EnumFinishes finish;
    String id;
    
    
    @Override
    public String toString() {
        return finish + " " + id;
    }
    
}
