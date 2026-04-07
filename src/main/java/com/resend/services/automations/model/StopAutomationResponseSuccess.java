package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private AutomationStatus status;

    public StopAutomationResponseSuccess() {
    }

    public StopAutomationResponseSuccess(String object, String id, AutomationStatus status) {
        this.object = object;
        this.id = id;
        this.status = status;
    }

    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public AutomationStatus getStatus() {
        return status;
    }
}
