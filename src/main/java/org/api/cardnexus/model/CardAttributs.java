package org.api.cardnexus.model;

import java.util.HashMap;
import java.util.List;

public class CardAttributs extends HashMap<String, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Integer getAsInt(String key) {
		var v = get(key);
		if(v==null)
			return null;
		
		if(v instanceof Integer i)
			return i;
		
		if(v instanceof Double i)
			return i.intValue();
		
		if(v instanceof String s)
			return Integer.parseInt(s);
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAsList(String key) {
		var v = get(key);
		if(v==null)
			return null;
		
		if(v instanceof List l)
			return l;
		
		if(v instanceof String s)
			return List.of(s.split(","));
		
		return null;
	}
	
	
	
	public Entry<String, Object> getEntryAt(int row) {
        var k = keySet().toArray()[row].toString();
        var v = get(k);
        
        return new Entry<String, Object>() {

			@Override
			public String getKey() {
				return k;
			}

			@Override
			public Object getValue() {
				return v;
			}

			@Override
			public Object setValue(Object value) {
				// TODO Auto-generated method stub
				return null;
			}
        	
        };
	}
}
