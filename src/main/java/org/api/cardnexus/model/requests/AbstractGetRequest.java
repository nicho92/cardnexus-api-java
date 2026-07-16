package org.api.cardnexus.model.requests;

import java.beans.Introspector;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import org.api.cardnexus.configuration.NexusConfig;

public abstract class AbstractGetRequest {
    private int limit = 100;

    public int getLimit() {
	return limit;
    }

    public void setLimit(int limit) {
	this.limit = limit;
    }

    private String encode(String value) {
	return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    public String toQueryString() {

	try {

	    var joiner = new StringJoiner("&");

	    for (var pd : Introspector.getBeanInfo(getClass(), Object.class).getPropertyDescriptors()) {
		var getter = pd.getReadMethod();
		if (getter == null) {
		    continue;
		}
		var value = getter.invoke(this);

		if (value != null) {
		    if (value instanceof LocalDate d)
			joiner.add(encode(pd.getName()) + "="+ encode(DateTimeFormatter.ofPattern(NexusConfig.REQ_DATE_PATTERN).format(d)));
		    else
			joiner.add(encode(pd.getName()) + "=" + encode(value.toString()));
		}
	    }

	    return joiner.toString();

	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

}
