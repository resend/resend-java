package com.resend.services.suppressions.model;

/**
 * Represents a successful response for removing a suppression from the suppression list.
 * Extends the RemovedSuppression class.
 */
public class RemoveSuppressionResponseSuccess extends RemovedSuppression {

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
        super(object, id, deleted);
    }
}
