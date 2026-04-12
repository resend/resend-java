package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response from listing automation runs.
 */
public class ListAutomationRunsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<AutomationRunListItem> data;

    /**
     * Default constructor for deserialization.
     */
    public ListAutomationRunsResponseSuccess() {
    }

    /**
     * Constructs a ListAutomationRunsResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param hasMore Whether more results are available.
     * @param data The list of automation runs.
     */
    public ListAutomationRunsResponseSuccess(String object, Boolean hasMore, List<AutomationRunListItem> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Indicates if there are more runs available for pagination.
     *
     * @return True if more runs are available, false otherwise.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Retrieves the list of automation runs.
     *
     * @return The list of run summaries.
     */
    public List<AutomationRunListItem> getData() {
        return data;
    }
}
