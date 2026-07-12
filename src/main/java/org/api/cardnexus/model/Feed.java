package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumFeedKey;

public class Feed {
    
    private EnumFeedKey feedType;
    private String url;
    private Date urlExpiresAt;
    private String checksum;
    private Long sizeBytes;
    private Long recordCount;
    private String format;
    private String encoding;
    private Date lastRefreshedAt;
    private Date generatedAt;
    
    
    @Override
    public String toString() {
           return getFeedType().toString();  
    }
    
    
    
    public EnumFeedKey getFeedType() {
        return feedType;
    }
    public void setFeedType(EnumFeedKey feedType) {
        this.feedType = feedType;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Date getUrlExpiresAt() {
        return urlExpiresAt;
    }
    public void setUrlExpiresAt(Date urlExpiresAt) {
        this.urlExpiresAt = urlExpiresAt;
    }
    public String getChecksum() {
        return checksum;
    }
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
    public Long getSizeBytes() {
        return sizeBytes;
    }
    public void setSizeBytes(Long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }
    public Long getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getEncoding() {
        return encoding;
    }
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    public Date getLastRefreshedAt() {
        return lastRefreshedAt;
    }
    public void setLastRefreshedAt(Date lastRefreshedAt) {
        this.lastRefreshedAt = lastRefreshedAt;
    }
    public Date getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(Date generatedAt) {
        this.generatedAt = generatedAt;
    }
    
    
    
    
    
    
    
    
    
}
