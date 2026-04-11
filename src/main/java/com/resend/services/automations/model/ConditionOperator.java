package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the comparison operator for condition steps.
 */
public enum ConditionOperator {
    /** Equals operator. */
    EQ("eq"),
    /** Not equals operator. */
    NEQ("neq"),
    /** Greater than operator. */
    GT("gt"),
    /** Greater than or equal operator. */
    GTE("gte"),
    /** Less than operator. */
    LT("lt"),
    /** Less than or equal operator. */
    LTE("lte"),
    /** Is empty operator. */
    IS_EMPTY("is_empty"),
    /** Is not empty operator. */
    IS_NOT_EMPTY("is_not_empty"),
    /** Contains operator. */
    CONTAINS("contains"),
    /** Not contains operator. */
    NOT_CONTAINS("not_contains"),
    /** Starts with operator. */
    STARTS_WITH("starts_with"),
    /** Ends with operator. */
    ENDS_WITH("ends_with"),
    /** In last operator (for time-based conditions). */
    IN_LAST("in_last"),
    /** Not in last operator (for time-based conditions). */
    NOT_IN_LAST("not_in_last");

    private final String value;

    ConditionOperator(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the operator.
     *
     * @return The operator value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}
