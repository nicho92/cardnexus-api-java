package org.api.cardnexus.model.requests;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumStatus;

public class ListCreationRequest {

    private String name;
    private String game=NexusConfig.DEFAULT_GAME_VALUE;
    private EnumStatus status;
    private String description;
    private boolean isPublic;
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public EnumStatus getStatus() {
        return status;
    }
    public void setStatus(EnumStatus status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isPublic() {
        return isPublic;
    }
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    
}
