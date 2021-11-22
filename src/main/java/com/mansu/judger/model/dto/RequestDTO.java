package com.mansu.judger.model.dto;

public class RequestDTO {
    private String requestType;
    private Object objectToSend;

    public RequestDTO(String requestType, Object objectToSend) {
        this.requestType = requestType;
        this.objectToSend = objectToSend;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Object getObjectToSend() {
        return objectToSend;
    }

    public void setObjectToSend(Object objectToSend) {
        this.objectToSend = objectToSend;
    }
}
