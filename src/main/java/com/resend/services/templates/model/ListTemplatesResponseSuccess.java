package com.resend.services.templates.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the response from listing templates.
 */
public class ListTemplatesResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<TemplateListItem> data;

    @JsonProperty("has_more")
    private Boolean hasMore;

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
    public ListTemplatesResponseSuccess(String object, List<TemplateListItem> data, Boolean hasMore) {
        this.object = object;
        this.data = data;
        this.hasMore = hasMore;
    }

    /**
     * Gets the object type.
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object type.
     *
     * @param object The object type.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Gets the list of templates.
     *
     * @return The list of templates.
     */
    public List<TemplateListItem> getData() {
        return data;
    }

    /**
     * Sets the list of templates.
     *
     * @param data The list of templates.
     */
    public void setData(List<TemplateListItem> data) {
        this.data = data;
    }

    /**
     * Gets whether there are more templates available.
     *
     * @return Whether there are more templates available.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Sets whether there are more templates available.
     *
     * @param hasMore Whether there are more templates available.
     */
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
