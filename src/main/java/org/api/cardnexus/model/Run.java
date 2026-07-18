package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumRunStatus;

public record Run (String id,EnumRunStatus status,Progress progress,RunResult result)
{
    	
}


