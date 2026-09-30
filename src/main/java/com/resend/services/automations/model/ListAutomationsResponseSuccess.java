package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response from listing automations.
 */
public class ListAutomationsResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    @JsonProperty("data")
    private @Nullable List<AutomationListItem> data;

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
    public ListAutomationsResponseSuccess(@Nullable String object, @Nullable Boolean hasMore, @Nullable List<AutomationListItem> data) {
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
     * Indicates if there are more automations available for pagination.
     *
     * @return True if more automations are available, false if not, or {@code null} if the API omitted the field.
     */
    public @Nullable Boolean hasMore() {
        return hasMore;
    }

    /**
     * Retrieves the list of automations.
     *
     * @return The list of automation summaries.
     */
    public @Nullable List<AutomationListItem> getData() {
        return data;
    }
}
