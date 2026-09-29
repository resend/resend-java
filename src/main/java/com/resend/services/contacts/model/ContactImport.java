package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a contact import record.
 */
public class ContactImport {

    @JsonProperty("object")
    private @Nullable String object;

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("status")
    private @Nullable String status;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("completed_at")
    private @Nullable String completedAt;

    @JsonProperty("counts")
    private @Nullable ContactImportCounts counts;

    /**
     * Default constructor.
     */
    public ContactImport() {
    }

    /**
     * Constructs a ContactImport with the provided values.
     *
     * @param object      The object type.
     * @param id          The contact import ID.
     * @param status      The status ({@code queued}, {@code in_progress}, {@code completed}, or {@code failed}).
     * @param createdAt   The creation timestamp.
     * @param completedAt The completion timestamp, or {@code null} if not yet completed.
     * @param counts      The per-status row counts.
     */
    public ContactImport(final @Nullable String object, final @Nullable String id, final @Nullable String status, final @Nullable String createdAt, final @Nullable String completedAt, final @Nullable ContactImportCounts counts) {
        this.object = object;
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.counts = counts;
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
     * Gets the contact import ID.
     *
     * @return The contact import ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the contact import status.
     *
     * @return The status ({@code queued}, {@code in_progress}, {@code completed}, or {@code failed}).
     */
    public @Nullable String getStatus() {
        return status;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public @Nullable String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the completion timestamp.
     *
     * @return The completion timestamp, or {@code null} if the import is not yet completed.
     */
    public @Nullable String getCompletedAt() {
        return completedAt;
    }

    /**
     * Gets the per-status row counts.
     *
     * @return The row counts.
     */
    public @Nullable ContactImportCounts getCounts() {
        return counts;
    }
}
