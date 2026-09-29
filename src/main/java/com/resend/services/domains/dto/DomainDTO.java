package com.resend.services.domains.dto;

import com.resend.services.domains.model.AbstractDomain;
import org.jspecify.annotations.Nullable;

/**
 * A Data Transfer Object (DTO) representing a domain. This class extends the AbstractDomain class
 * and is used for transferring domain-related data.
 */
public class DomainDTO extends AbstractDomain {

    /**
     * Default constructor for creating an empty DomainDTO object.
     */
    public DomainDTO() {
    }

    /**
     * Constructor to create a DomainDTO object with the provided attributes.
     *
     * @param id          The ID of the domain.
     * @param name        The name of the domain.
     * @param createdAt   The creation timestamp of the domain.
     * @param status      The status of the domain.
     * @param region      The region of the domain.
     */
    public DomainDTO(@Nullable String id, @Nullable String name, @Nullable String createdAt, @Nullable String status, @Nullable String region) {
        super(id, name, createdAt, status, region);
    }
}

