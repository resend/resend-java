package com.resend.services.suppressions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a successful response for adding email addresses to the suppression list.
 */
public class AddSuppressionsResponseSuccess {

    @JsonProperty("data")
    private @Nullable List<AddedSuppression> data;

    /**
     * Default constructor
     */
    public AddSuppressionsResponseSuccess() {

    }

    /**
     * Constructs a successful response for adding suppressions.
     *
     * @param data The list of added suppressions.
     */
    public AddSuppressionsResponseSuccess(@Nullable List<AddedSuppression> data) {
        this.data = data;
    }

    /**
     * Get the list of added suppressions.
     *
     * @return The list of added suppressions.
     */
    public @Nullable List<AddedSuppression> getData() {
        return data;
    }
}
