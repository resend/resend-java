package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a contact import record.
 */
public class ContactImport {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("completed_at")
    private String completedAt;

    @JsonProperty("counts")
    private ContactImportCounts counts;

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
    public ContactImport(final String object, final String id, final String status, final String createdAt, final String completedAt, final ContactImportCounts counts) {
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
    public String getObject() {
        return object;
    }

    /**
     * Gets the contact import ID.
     *
     * @return The contact import ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the contact import status.
     *
     * @return The status ({@code queued}, {@code in_progress}, {@code completed}, or {@code failed}).
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the completion timestamp.
     *
     * @return The completion timestamp, or {@code null} if the import is not yet completed.
     */
    public String getCompletedAt() {
        return completedAt;
    }

    /**
     * Gets the per-status row counts.
     *
     * @return The row counts.
     */
    public ContactImportCounts getCounts() {
        return counts;
    }
}
