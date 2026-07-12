package org.api.cardnexus.model;

public class MarketPrice {
    private double low;
    private double mid;
    private double marketValue;
    private double change24h;
    private double change7d;
    private double change30d;
    
    
    public double getLow() {
        return low;
    }
    public void setLow(double low) {
        this.low = low;
    }
    public double getMid() {
        return mid;
    }
    public void setMid(double mid) {
        this.mid = mid;
    }
    public double getMarketValue() {
        return marketValue;
    }
    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }
    public double getChange24h() {
        return change24h;
    }
    public void setChange24h(double change24h) {
        this.change24h = change24h;
    }
    public double getChange7d() {
        return change7d;
    }
    public void setChange7d(double change7d) {
        this.change7d = change7d;
    }
    public double getChange30d() {
        return change30d;
    }
    public void setChange30d(double change30d) {
        this.change30d = change30d;
    }
    
    
}
