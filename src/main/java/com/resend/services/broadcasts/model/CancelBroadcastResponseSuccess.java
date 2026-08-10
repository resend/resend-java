package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelBroadcastResponseSuccess extends BaseBroadcastResponse {
    @JsonProperty("object")
    private String object;

    public CancelBroadcastResponseSuccess() {

    }

    public CancelBroadcastResponseSuccess(String id, String object) {
        super(id);
        this.object = object;
    }

    public String getObject() {
        return object;
    }
}
