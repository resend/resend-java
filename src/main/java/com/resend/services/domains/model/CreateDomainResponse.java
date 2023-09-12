package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a response object for creating a domain. This class extends the AbstractDomain class
 * and includes additional attributes specific to a create domain response.
 */
public class CreateDomainResponse extends AbstractDomain {

    /**
     * The list of records associated with the created domain.
     */
    @JsonProperty("records")
    private List<Record> records;

    /**
     * The DNS provider of the domain.
     */
    @JsonProperty("dnsProvider")
    private String dnsProvider;

    /**
     * Default constructor for creating an empty CreateDomainResponse object.
     */
    public CreateDomainResponse() {

    }

    /**
     * Constructor to create a CreateDomainResponse object with the provided attributes.
     *
     * @param id          The ID of the domain.
     * @param name        The name of the domain.
     * @param createdAt   The creation timestamp of the domain.
     * @param status      The status of the domain.
     * @param region      The region of the domain.
     * @param dnsProvider The DNS provider of the domain.
     * @param records     The list of records associated with the created domain.
     */
    public CreateDomainResponse(final String id,
                                final String name,
                                final OffsetDateTime createdAt,
                                final String status,
                                final String region,
                                final String dnsProvider,
                                final List<Record> records) {
        super(id, name, createdAt, status, region);
        this.records = records;
        this.dnsProvider = dnsProvider;
    }

    /**
     * Get the list of records associated with the created domain.
     *
     * @return The list of records.
     */
    public List<Record> getRecords() {
        return records;
    }


    /**
     * Get the DNS provider of the domain.
     *
     * @return The DNS provider.
     */
    public String getDnsProvider() {
        return dnsProvider;
    }
}