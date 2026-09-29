package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a DNS record associated with a domain.
 *
 * <p>The {@code record} field identifies which kind of DNS record this is
 * (e.g. {@code SPF}, {@code DKIM}, {@code Receiving}, {@code Tracking},
 * {@code TrackingCAA}). The {@code type} field is the underlying DNS
 * record type (e.g. {@code MX}, {@code TXT}, {@code CNAME}, {@code CAA}).</p>
 */
public class Record {

    @JsonProperty("record")
    private @Nullable String record;

    @JsonProperty("name")
    private @Nullable String name;

    @JsonProperty("type")
    private @Nullable String type;

    @JsonProperty("ttl")
    private @Nullable String ttl;

    @JsonProperty("status")
    private @Nullable String status;

    @JsonProperty("value")
    private @Nullable String value;

    @JsonProperty("priority")
    private int priority;

    /**
     * Empty constructor.
     */
    public Record() {
    }

    /**
     * Constructor to create an immutable Record instance.
     *
     * @param record The record type.
     * @param name The record name.
     * @param type The record type.
     * @param ttl The TTL value.
     * @param status The status of the record.
     * @param value The record value.
     * @param priority The priority of the record. (Optional)
     */
    public Record(final @Nullable String record,
                  final @Nullable String name,
                  final @Nullable String type,
                  final @Nullable String ttl,
                  final @Nullable String status,
                  final @Nullable String value,
                  final int priority) {
        this.record = record;
        this.name = name;
        this.type = type;
        this.ttl = ttl;
        this.status = status;
        this.value = value;
        this.priority = priority;
    }

    /**
     * Get the record type.
     *
     * @return The record type.
     */
    public @Nullable String getRecord() {
        return record;
    }

    /**
     * Get the record name.
     *
     * @return The record name.
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Get the record type.
     *
     * @return The record type.
     */
    public @Nullable String getType() {
        return type;
    }

    /**
     * Get the TTL value.
     *
     * @return The TTL value.
     */
    public @Nullable String getTtl() {
        return ttl;
    }

    /**
     * Get the status of the record.
     *
     * @return The status of the record.
     */
    public @Nullable String getStatus() {
        return status;
    }

    /**
     * Get the record value.
     *
     * @return The record value.
     */
    public @Nullable String getValue() {
        return value;
    }

    /**
     * Get the priority of the record. (Optional)
     *
     * @return The priority of the record.
     */
    public int getPriority() {
        return priority;
    }
}
