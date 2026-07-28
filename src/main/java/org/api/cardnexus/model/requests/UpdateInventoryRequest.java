package org.api.cardnexus.model.requests;

import java.util.HashMap;
import java.util.Map;

import org.api.cardnexus.model.Grading;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class UpdateInventoryRequest {

    private EnumCondition condition;
    private EnumFinishes finish;
    private String language;
    private Grading graded;
    private String customId;
    private String comment;
    private String notes;
    private String location;
    private Map<String, String[]> tags;
    private Map<String,Integer> quantity;
    
  private UpdateInventoryRequest()
  {
      
  }
    
  public UpdateInventoryRequest setQuantity(Integer qty) {
      quantity = new HashMap<>();
      quantity.put("set", qty);
      return this;
  }
  
  public UpdateInventoryRequest changeQuantity(Integer delta) {
      quantity = new HashMap<>();
      quantity.put("adjust", delta);
      return this;
  }
  
  
 public UpdateInventoryRequest clearTags() {
        
  	if(tags==null)
  	    tags = new HashMap<>();
  	
  	tags.put("set", new String[] {});
  	
  	
          return this;
    }
    
    public UpdateInventoryRequest addTags(String[] tag) {
        
	if(tags==null)
	    tags = new HashMap<>();
	
	tags.put("add", tag);
	
	
        return this;
    }
    
    public UpdateInventoryRequest removeTags(String[] tag) {
        
  	if(tags==null)
  	    tags = new HashMap<>();
  	
  	tags.put("remove", tag);
  	
  	
          return this;
      }
    
    public UpdateInventoryRequest setTags(String[] tag) {
        
  	if(tags==null)
  	    tags = new HashMap<>();
  	
  	tags.put("set", tag);
  	
  	
          return this;
      }
    

    
    public UpdateInventoryRequest setCondition(EnumCondition condition) {
        this.condition = condition;
        return this;
    }

    public UpdateInventoryRequest setFinish(EnumFinishes finish) {
        this.finish = finish;
        return this;
    }

    public UpdateInventoryRequest setLanguage(String language) {
        this.language = language;
        return this;
    }

    public UpdateInventoryRequest setGraded(Grading graded) {
        this.graded = graded;
        return this;
    }

    public UpdateInventoryRequest setCustomId(String customId) {
        this.customId = customId;
        return this;
    }

    public UpdateInventoryRequest setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public UpdateInventoryRequest setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public UpdateInventoryRequest setLocation(String location) {
        this.location = location;
        return this;
    }

    public static UpdateInventoryRequest create()
    {
	return new	 UpdateInventoryRequest();
    }
    
    
}
