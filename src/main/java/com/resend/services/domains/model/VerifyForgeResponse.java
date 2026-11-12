package com.resend.services.domains.model;

/**
 * Represents a response object for a Forge verification operation. This class extends the
 * AbstractDomainAction class and is used to indicate that verification was triggered.
 */
public class VerifyForgeResponse extends AbstractDomainAction {

    /**
     * Default constructor for creating an empty VerifyForgeResponse object.
     */
    public VerifyForgeResponse() {
    }

    /**
     * Constructor to create a VerifyForgeResponse object with the provided attributes.
     *
     * @param object The object type of the response.
     * @param id     The unique identifier associated with the domain.
     */
    public VerifyForgeResponse(String object, String id) {
        super(object, id);
    }
}
