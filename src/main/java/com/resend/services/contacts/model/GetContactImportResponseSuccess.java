package com.resend.services.contacts.model;

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
    public GetContactImportResponseSuccess(final String object, final String id, final String status, final String createdAt, final String completedAt, final ContactImportCounts counts) {
        super(object, id, status, createdAt, completedAt, counts);
    }
}
