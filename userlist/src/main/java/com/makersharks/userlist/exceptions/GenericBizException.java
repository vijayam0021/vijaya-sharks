package com.makersharks.userlist.exceptions;

public class GenericBizException extends RuntimeException{

    private String message;
    private String status = "Error";

    public GenericBizException() {}

    public GenericBizException(String msg) {
        super(msg);
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
