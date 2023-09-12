package com.resend.services.domains.dto;

import com.resend.services.domains.model.AbstractDomain;

import java.time.OffsetDateTime;

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
    public DomainDTO(String id, String name, OffsetDateTime createdAt, String status, String region) {
        super(id, name, createdAt, status, region);
    }
}

