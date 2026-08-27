package org.api.cardnexus.model.tech;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

public class NexusHeader implements Serializable {

	private static final long serialVersionUID = 1L;
	private int limit;
	private int remaining;
	private String id;
	private long epoch;
	private String url;
	private String method;
	
	public static NexusHeader build(HttpRequestBase request, CloseableHttpResponse response)
    {
		var header = new NexusHeader();
			header.setRateLimit(Integer.parseInt(response.getFirstHeader("X-RateLimit-Limit").getValue()));
			header.setRemaining(Integer.parseInt(response.getFirstHeader("X-RateLimit-Remaining").getValue()));
			header.setRequestId(response.getFirstHeader("X-Request-Id").getValue());
			header.setEpochReset(Long.parseLong(response.getFirstHeader("X-RateLimit-Reset").getValue()));
	    	header.setEndpoint(request.getRequestLine().getUri());
	    	header.setMethod(request.getMethod());
 		return header;
    	
    }
	
	public LocalDateTime getResetTime() {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), ZoneId.systemDefault());
	}
	
	private NexusHeader() {
		//use build method to create an instance
	}
	
	public String toString() {
		return "NexusHeader [limit=" + limit + ", remaining=" + remaining + ", id=" + id + ", epoch=" + getResetTime() + "]";
	}
	private void setMethod(String method) {
		this.method = method;
	}
	public void setEndpoint(String url) {
		this.url=url;
	}
	private void setRateLimit(int limmit) {
		this.limit = limmit;
	}
	private void setRemaining(int remain) {
		this.remaining = remain;
	}
	private void setRequestId(String id) {
		this.id = id;
	}
	private void setEpochReset(long epoch) {
		this.epoch=epoch;
	}
	public long getEpoch() {
		return epoch;
	}
	public String getId() {
		return id;
	}
	public int getLimit() {
		return limit;
	}
	public int getRemaining() {
		return remaining;
	}
	public String getEndpoint() {
		return url;
	}
	public String getMethod() {
		return method;
	}
	
}
