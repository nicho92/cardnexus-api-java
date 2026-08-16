package org.api.cardnexus.model;

public record NexusError(String code, Integer status, String message, ErrorData data) {

}

record ErrorData(String bucket, Integer limit, Integer retryAfter)
{
    
}
