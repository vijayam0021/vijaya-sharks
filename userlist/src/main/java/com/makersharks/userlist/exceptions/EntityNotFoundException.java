package com.makersharks.userlist.exceptions;

import com.makersharks.userlist.Utils.ApplicationConstants;

public class EntityNotFoundException extends GenericBizException{

    private String details;

    public EntityNotFoundException() {
        super(ApplicationConstants.ENTITY_NOT_FOUND);
        this.details = "";
    }
    public EntityNotFoundException(String details) {
        super(ApplicationConstants.ENTITY_NOT_FOUND);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
