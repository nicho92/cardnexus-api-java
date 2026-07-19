package org.api.cardnexus.model.requests;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumStatus;

public class ListCreationRequest {

    private String name;
    private String game=NexusConfig.DEFAULT_GAME_VALUE;
    private EnumStatus status;
    private String description;
    private boolean isPublic;
    
    
    private ListCreationRequest()
    {
	
    }
    
    public static ListCreationRequest create()
    {
	return new ListCreationRequest();
    }
    
    public String getName() {
        return name;
    }
    public ListCreationRequest setName(String name) {
        this.name = name;
        return this;
    }
    public String getGame() {
        return game;
    }
    public ListCreationRequest setGame(String game) {
        this.game = game;
        return this;
    }
    public EnumStatus getStatus() {
        return status;
    }
    public ListCreationRequest setStatus(EnumStatus status) {
        this.status = status;
        return this;
    }
    public String getDescription() {
        return description;
    }
    public ListCreationRequest setDescription(String description) {
        this.description = description;
        return this;
    }
    public boolean isPublic() {
        return isPublic;
    }
    public ListCreationRequest setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }
    
    
}
