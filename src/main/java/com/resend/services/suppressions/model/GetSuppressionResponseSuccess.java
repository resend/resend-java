package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a successful response for retrieving a suppression.
 */
public class GetSuppressionResponseSuccess {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Default constructor
     */
    public GetSuppressionResponseSuccess() {

    }

    /**
     * Constructs a successful response for retrieving a suppression.
     *
     * @param object    The object type of the suppression.
     * @param id        The ID of the suppression.
     * @param email     The suppressed email address.
     * @param origin    The origin of the suppression.
     * @param sourceId  The ID of the email that triggered the suppression.
     * @param createdAt The creation timestamp of the suppression.
     */
    public GetSuppressionResponseSuccess(String object, String id, String email, String origin, String sourceId, String createdAt) {
        this.object = object;
        this.id = id;
        this.email = email;
        this.origin = origin;
        this.sourceId = sourceId;
        this.createdAt = createdAt;
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
     * Get the suppressed email address.
     *
     * @return The suppressed email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the origin of the suppression.
     *
     * @return The origin of the suppression.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Get the ID of the email that triggered the suppression.
     * For suppressions with a manual origin, the source ID is null.
     *
     * @return The ID of the email that triggered the suppression.
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * Get the creation timestamp of the suppression.
     *
     * @return The creation timestamp of the suppression.
     */
    public String getCreatedAt() {
        return createdAt;
    }
}
