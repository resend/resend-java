package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for removing a suppression from the suppression list.
 */
public class RemoveSuppressionResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private Boolean deleted;

    /**
     * Default constructor
     */
    public RemoveSuppressionResponseSuccess() {

    }

    /**
     * Constructs a successful response for removing a suppression.
     *
     * @param object  The object type of the suppression.
     * @param id      The ID of the suppression.
     * @param deleted Whether the suppression was deleted.
     */
    public RemoveSuppressionResponseSuccess(String object, String id, Boolean deleted) {
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
