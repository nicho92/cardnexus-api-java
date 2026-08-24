package org.api.cardnexus.model.attributs;

import java.util.List;

import org.api.cardnexus.model.AbstractCardAttributs;

public class NarutoAttributs extends AbstractCardAttributs {

    private String cardType;
    private String group;
    private String orientation;
    private int chakra;
    private int power;
    private List<String> keywords;
    private String rulesText;
    private String version;
    
    
    
    public String getCardType() {
        return cardType;
    }
    public String getGroup() {
        return group;
    }
    public String getOrientation() {
        return orientation;
    }
    public int getChakra() {
        return chakra;
    }
    public int getPower() {
        return power;
    }
    public List<String> getKeywords() {
        return keywords;
    }
    public String getRulesText() {
        return rulesText;
    }
    public String getVersion() {
        return version;
    }
    


}
