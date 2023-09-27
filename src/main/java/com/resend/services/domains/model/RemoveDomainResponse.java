package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response object for a domain deletion operation. This class extends the AbstractDomainAction class
 * and includes a boolean flag indicating whether the deletion was successful.
 */
public class RemoveDomainResponse extends AbstractDomainAction {

    /**
     * A boolean flag indicating whether the domain was successfully deleted.
     */
    @JsonProperty("deleted")
    public boolean deleted;

    /**
     * Default constructor for creating an empty RemoveDomainResponse object.
     */
    public RemoveDomainResponse() {
    }

    /**
     * Constructor to create a RemoveDomainResponse object with the provided attributes.
     *
     * @param object  The object type of the response.
     * @param id      The unique identifier associated with the domain.
     * @param deleted A boolean flag indicating whether the domain was successfully deleted.
     */
    public RemoveDomainResponse(String object, String id, boolean deleted) {
        super(object, id);
        this.deleted = deleted;
    }

    /**
     * Get the boolean flag indicating whether the domain was successfully deleted.
     *
     * @return True if the domain was successfully deleted; otherwise, false.
     */
    public boolean isDeleted() {
        return deleted;
    }
}