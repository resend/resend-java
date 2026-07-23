package com.resend.services.suppressions.model;

/**
 * Represents a successful response for adding an email address to the suppression list.
 * Extends the AddedSuppression class.
 */
public class AddSuppressionResponseSuccess extends AddedSuppression {

    /**
     * Default constructor
     */
    public AddSuppressionResponseSuccess() {

    }

    /**
     * Constructs a successful response for adding a suppression.
     *
     * @param object The object type of the suppression.
     * @param id     The ID of the suppression.
     */
    public AddSuppressionResponseSuccess(String object, String id) {
        super(object, id);
    }
}
