package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a suppression removed from the suppression list.
 */
public class RemovedSuppression {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor
     */
    public RemovedSuppression() {

    }

    /**
     * Constructs a removed suppression.
     *
     * @param object  The object type of the suppression.
     * @param id      The ID of the suppression.
     * @param deleted Whether the suppression was deleted.
     */
    public RemovedSuppression(String object, String id, Boolean deleted) {
        this.object = object;
        this.id = id;
        this.deleted = deleted;
    }

    /**
     * Get the object type.
     *
     * @return The object type of the suppression.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the suppression.
     *
     * @return The ID of the suppression.
     */
    public String getId() {
        return id;
    }

    /**
     * Get whether the suppression was deleted.
     *
     * @return Whether the suppression was deleted.
     */
    public Boolean getDeleted() {
        return deleted;
    }
}
