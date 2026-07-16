package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumFeedKey;

public record Feed (EnumFeedKey feedType,String url,Date urlExpiresAt,String checksum,Long sizeBytes,Long recordCount,String format,String encoding,Date lastRefreshedAt,Date generatedAt)
{
}
