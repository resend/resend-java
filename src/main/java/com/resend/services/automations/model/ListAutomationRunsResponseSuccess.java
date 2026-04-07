package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListAutomationRunsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<AutomationRunListItem> data;

    public ListAutomationRunsResponseSuccess() {
    }

    public ListAutomationRunsResponseSuccess(String object, Boolean hasMore, List<AutomationRunListItem> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    public String getObject() {
        return object;
    }

    public Boolean hasMore() {
        return hasMore;
    }

    public List<AutomationRunListItem> getData() {
        return data;
    }
}
