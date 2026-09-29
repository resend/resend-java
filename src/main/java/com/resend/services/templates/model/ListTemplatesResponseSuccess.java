package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents the response from listing templates.
 */
public class ListTemplatesResponseSuccess {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("data")
    private @Nullable List<TemplateListItem> data;

    @JsonProperty("has_more")
    private @Nullable Boolean hasMore;

    /**
     * Default constructor.
     */
    public ListTemplatesResponseSuccess() {
    }

    /**
     * Constructs a ListTemplatesResponse with the specified attributes.
     *
     * @param object  The object type.
     * @param data    The list of templates.
     * @param hasMore Whether there are more templates available.
     */
    public ListTemplatesResponseSuccess(@Nullable String object, @Nullable List<TemplateListItem> data, @Nullable Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(@Nullable String object) {
        this.object = object;
    }

    /**
     * Gets the list of templates.
     *
     * @return The list of templates.
     */
    public @Nullable List<TemplateListItem> getData() {
        return data;
    }

    /**
     * Sets the list of templates.
     *
     * @param data The list of templates.
     */
    public void setData(@Nullable List<TemplateListItem> data) {
        this.data = data;
    }

    /**
     * Gets whether there are more templates available.
     *
     * @return Whether there are more templates available.
     */
    public @Nullable Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Sets whether there are more templates available.
     *
     * @param hasMore Whether there are more templates available.
     */
    public void setHasMore(@Nullable Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
