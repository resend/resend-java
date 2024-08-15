package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a domain object.
 */
public class Domain extends AbstractDomain {

    /**
     * The object of the domain.
     */
    @JsonProperty("object")
    private String object;

    /**
     * The records of the domain.
     */
    @JsonProperty("records")
    private List<Record> records;

    /**
     * The DNS provider of the domain.
     */
    @JsonProperty("dnsProvider")
    private String dnsProvider;

    /**
     * Empty constructor.
     */
    public Domain() {

    }

    /**
     * Constructor to create an immutable Domain instance.
     *
     * @param id The ID of the domain.
     * @param name The name of the domain.
     * @param createdAt The creation timestamp of the domain.
     * @param status The status of the domain.
     * @param region The region of the domain.
     * @param dnsProvider The dnsProvider of the domain.
     * @param object The type of the object (e.g., "domain").
     * @param records The list of DNS records associated with the domain.
     */
    public Domain(String id,
                  String name,
                  String createdAt,
                  String status,
                  String region,
                  String dnsProvider,
                  String object,
                  List<Record> records) {
        super(id, name, createdAt, status, region);
        this.object = object;
        this.records = records;
        this.dnsProvider = dnsProvider;
    }

    /**
     * Get the type of the object.
     *
     * @return The type of the object.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the records of the domain.
     *
     * @return The records of the domain.
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

