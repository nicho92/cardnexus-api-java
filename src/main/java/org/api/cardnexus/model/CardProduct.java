package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumRarity;

public class CardProduct extends AbstractProduct {

    private String printNumber;
    private EnumRarity rarity;
    private List<EnumFinishes> finishes;
    
    
    
    public String getPrintNumber() {
        return printNumber;
    }
    public void setPrintNumber(String printNumber) {
        this.printNumber = printNumber;
    }
    public EnumRarity getRarity() {
        return rarity;
    }
    public void setRarity(EnumRarity rarity) {
        this.rarity = rarity;
    }
    public List<EnumFinishes> getFinishes() {
        return finishes;
    }
    public void setFinishes(List<EnumFinishes> finishes) {
        this.finishes = finishes;
    }
    
    
    
    
}
