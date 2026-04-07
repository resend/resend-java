package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListAutomationsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<AutomationListItem> data;

    public ListAutomationsResponseSuccess() {
    }

    public ListAutomationsResponseSuccess(String object, Boolean hasMore, List<AutomationListItem> data) {
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

    public List<AutomationListItem> getData() {
        return data;
    }
}
