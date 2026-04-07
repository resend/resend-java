package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteAutomationResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    public DeleteAutomationResponseSuccess() {
    }

    public DeleteAutomationResponseSuccess(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
