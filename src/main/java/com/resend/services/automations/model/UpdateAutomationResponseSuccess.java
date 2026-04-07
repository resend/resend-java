package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    public UpdateAutomationResponseSuccess() {
    }

    public UpdateAutomationResponseSuccess(String object, String id) {
        this.object = object;
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }
}
