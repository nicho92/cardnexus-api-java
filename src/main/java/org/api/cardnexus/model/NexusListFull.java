package org.api.cardnexus.model;

import java.util.List;

public class NexusListFull extends NexusList{

    private List<ListItem> items;
    
    public List<ListItem> getItems() {
 	return items;
     }
     
     public void setItems(List<ListItem> items) {
 	this.items = items;
     }
     
}
