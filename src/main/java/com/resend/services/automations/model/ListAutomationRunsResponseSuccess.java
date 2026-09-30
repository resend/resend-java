package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response from listing automation runs.
 */
public class ListAutomationRunsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<AutomationRunListItem> data;

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
    public ListAutomationRunsResponseSuccess(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<AutomationRunListItem> data) {
        this.object = object;
        this.hasMore = hasMore;
        this.data = data;
    }

    /**
     * Retrieves the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Indicates if there are more runs available for pagination.
     *
     * @return True if more runs are available, false if not, or {@code null} if the API omitted the field.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Retrieves the list of automation runs.
     *
     * @return The list of run summaries.
     */
    public @Nullable List<AutomationRunListItem> getData() {
        return data;
    }
}
