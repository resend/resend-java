package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    public CreateAutomationResponseSuccess() {
    }

    public CreateAutomationResponseSuccess(String object, String id) {
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
