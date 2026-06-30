package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the TXT DNS record returned within a domain claim response.
 */
public class DomainClaimRecord {

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

    @JsonProperty("ttl")
    private String ttl;

    /**
     * Default constructor.
     */
    public DomainClaimRecord() {
    }

    /**
     * Constructs a DomainClaimRecord with all fields.
     *
     * @param type  The DNS record type.
     * @param name  The DNS record name.
     * @param value The DNS record value.
     * @param ttl   The TTL for the DNS record.
     */
    public DomainClaimRecord(final String type,
                             final String name,
                             final String value,
                             final String ttl) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.ttl = ttl;
    }

    /**
     * Get the DNS record type.
     *
     * @return The DNS record type.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the DNS record name.
     *
     * @return The DNS record name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the DNS record value.
     *
     * @return The DNS record value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the TTL for the DNS record.
     *
     * @return The TTL.
     */
    public String getTtl() {
        return ttl;
    }
}
