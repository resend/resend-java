package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a suppression from the suppression list.
 */
public class Suppression {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("email")
    private @Nullable String email;

    @JsonProperty("origin")
    private @Nullable String origin;

    @JsonProperty("source_id")
    private @Nullable String sourceId;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    /**
     * Default constructor
     */
    public Suppression() {

    }

    /**
     * Constructs a suppression.
     *
     * @param object    The object type of the suppression.
     * @param id        The ID of the suppression.
     * @param email     The suppressed email address.
     * @param origin    The origin of the suppression.
     * @param sourceId  The ID of the email that triggered the suppression.
     * @param createdAt The creation timestamp of the suppression.
     */
    public Suppression(@Nullable String object, @Nullable String id, @Nullable String email, @Nullable String origin, @Nullable String sourceId, @Nullable String createdAt) {
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
    public @Nullable String getObject() {
        return object;
    }

    /**
     * Get the ID of the suppression.
     *
     * @return The ID of the suppression.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Get the suppressed email address.
     *
     * @return The suppressed email address.
     */
    public @Nullable String getEmail() {
        return email;
    }

    /**
     * Get the origin of the suppression.
     *
     * @return The origin of the suppression.
     */
    public @Nullable String getOrigin() {
        return origin;
    }

    /**
     * Get the ID of the email that triggered the suppression.
     * For suppressions with a manual origin, the source ID is null.
     *
     * @return The ID of the email that triggered the suppression.
     */
    public @Nullable String getSourceId() {
        return sourceId;
    }

    /**
     * Get the creation timestamp of the suppression.
     *
     * @return The creation timestamp of the suppression.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }
}
