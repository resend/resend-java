package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a successful response from listing automations.
 */
public class ListAutomationsResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<AutomationListItem> data;

    /**
     * Default constructor for deserialization.
     */
    public ListAutomationsResponseSuccess() {
    }

    /**
     * Constructs a ListAutomationsResponseSuccess with specified values.
     *
     * @param object The object type.
     * @param hasMore Whether more results are available.
     * @param data The list of automations.
     */
    public ListAutomationsResponseSuccess(String object, Boolean hasMore, List<AutomationListItem> data) {
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
     * Indicates if there are more automations available for pagination.
     *
     * @return True if more automations are available, false otherwise.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Retrieves the list of automations.
     *
     * @return The list of automation summaries.
     */
    public List<AutomationListItem> getData() {
        return data;
    }
}
