package org.api.cardnexus.model;

import java.util.List;

public record RunResult(String region,String currency,List<RunOption> options,List<User> sellers)
{
 
}
