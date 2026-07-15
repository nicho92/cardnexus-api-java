package org.api.cardnexus.model;

import java.util.List;

public class Progress {
    
    private double fraction;
    private List<RunOption> options;
    public double getFraction() {
        return fraction;
    }
    public void setFraction(double fraction) {
        this.fraction = fraction;
    }
    public List<RunOption> getOptions() {
        return options;
    }
    public void setOptions(List<RunOption> options) {
        this.options = options;
    }
    
    
}
