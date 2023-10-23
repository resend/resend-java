package com.resend.services.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a batch email entity.
 */
public class BatchEmail {

    @JsonProperty("id")
    private String id;

    /**
     * Default constructor.
     */
    public BatchEmail() {
    }

    /**
     * Constructor with ID.
     *
     * @param id The ID of the batch email.
     */
    public BatchEmail(final String id) {
        this.id = id;
    }

    /**
     * Get the ID of the batch email.
     *
     * @return The ID of the batch email.
     */
    public String getId() {
        return id;
    }
}

