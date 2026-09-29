package com.resend.services.contacts.model;

import org.jspecify.annotations.Nullable;


/**
 * Represents a successful response for retrieving a single contact import.
 */
public class GetContactImportResponseSuccess extends ContactImport {

    /**
     * Default constructor.
     */
    public GetContactImportResponseSuccess() {
        super();
    }

    /**
     * Constructs a GetContactImportResponseSuccess with the provided values.
     *
     * @param object      The object type.
     * @param id          The contact import ID.
     * @param status      The status.
     * @param createdAt   The creation timestamp.
     * @param completedAt The completion timestamp, or {@code null} if not yet completed.
     * @param counts      The per-status row counts.
     */
    public GetContactImportResponseSuccess(final @Nullable String object, final @Nullable String id, final @Nullable String status, final @Nullable String createdAt, final @Nullable String completedAt, final @Nullable ContactImportCounts counts) {
        super(object, id, status, createdAt, completedAt, counts);
    }
}
