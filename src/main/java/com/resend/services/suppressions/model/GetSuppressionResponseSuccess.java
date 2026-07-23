package com.resend.services.suppressions.model;

/**
 * Represents a successful response for retrieving a suppression.
 * Extends the Suppression class.
 */
public class GetSuppressionResponseSuccess extends Suppression {

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
        super(object, id, email, origin, sourceId, createdAt);
    }
}
