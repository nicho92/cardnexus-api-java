package org.api.cardnexus.model;

import java.util.List;

import org.api.cardnexus.tools.Utils;

public record Progress (double fraction,List<RunOption> options)
{

    @Override
    public String toString() {
        return Utils.format(fraction()*100) +"%";
    }
    
}
    
    
    

