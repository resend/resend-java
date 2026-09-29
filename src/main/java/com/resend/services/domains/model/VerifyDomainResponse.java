package com.resend.services.domains.model;

import org.jspecify.annotations.Nullable;


/**
 * Represents a response object for a domain verification operation. This class extends the AbstractDomainAction class
 * and is used to indicate the result of the verification.
 */
public class VerifyDomainResponse extends AbstractDomainAction {

    /**
     * Default constructor for creating an empty VerifyDomainResponse object.
     */
    public VerifyDomainResponse() {
    }

    /**
     * Constructor to create a VerifyDomainResponse object with the provided attributes.
     *
     * @param object The object type of the response.
     * @param id     The unique identifier associated with the domain.
     */
    public VerifyDomainResponse(@Nullable String object, @Nullable String id) {
        super(object, id);
    }
}