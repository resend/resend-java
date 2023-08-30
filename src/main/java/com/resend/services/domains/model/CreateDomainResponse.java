package com.resend.services.domains.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a response for creating a domain.
 */
public class CreateDomainResponse {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("createdAt")
    private final OffsetDateTime createdAt;

    @JsonProperty("status")
    private final String status;

    @JsonProperty("records")
    private final List<Record> records;

    @JsonProperty("region")
    private final String region;

    @JsonProperty("dnsProvider")
    private final String dnsProvider;

    /**
     * Constructor to create an immutable CreateDomainResponse instance.
     *
     * @param id The ID of the domain.
     * @param name The name of the domain.
     * @param createdAt The creation timestamp of the domain.
     * @param status The status of the domain.
     * @param records The list of DNS records associated with the domain.
     * @param region The region of the domain.
     * @param dnsProvider The DNS provider of the domain.
     */
    public CreateDomainResponse(final String id,
                                final String name,
                                final OffsetDateTime createdAt,
                                final String status,
                                final List<Record> records,
                                final String region,
                                final String dnsProvider) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.status = status;
        this.records = records;
        this.region = region;
        this.dnsProvider = dnsProvider;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public List<Record> getRecords() {
        return records;
    }

    public String getRegion() {
        return region;
    }

    public String getDnsProvider() {
        return dnsProvider;
    }
}