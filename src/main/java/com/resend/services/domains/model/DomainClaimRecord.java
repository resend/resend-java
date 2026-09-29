package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the TXT DNS record returned within a domain claim response.
 */
public class DomainClaimRecord {

    @JsonProperty("type")
    private @Nullable String type;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("value")
    private @Nullable String value;

    @JsonProperty("ttl")
    private @Nullable String ttl;

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
    public DomainClaimRecord(final @Nullable String type,
                             final @Nullable String name,
                             final @Nullable String value,
                             final @Nullable String ttl) {
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
    public @Nullable String getType() {
        return type;
    }

    /**
     * Get the DNS record name.
     *
     * @return The DNS record name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Get the DNS record value.
     *
     * @return The DNS record value.
     */
    public @Nullable String getValue() {
        return value;
    }

    /**
     * Get the TTL for the DNS record.
     *
     * @return The TTL.
     */
    public @Nullable String getTtl() {
        return ttl;
    }
}
