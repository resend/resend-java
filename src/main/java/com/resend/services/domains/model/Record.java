package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a DNS record associated with a domain.
 */
public class Record {

    @JsonProperty("record")
    private final String record;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("type")
    private final String type;

    @JsonProperty("ttl")
    private final String ttl;

    @JsonProperty("status")
    private final String status;

    @JsonProperty("value")
    private final String value;

    @JsonProperty("priority")
    private final int priority;

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
    public Record(final String record,
                  final String name,
                  final String type,
                  final String ttl,
                  final String status,
                  final String value,
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
    public String getRecord() {
        return record;
    }

    /**
     * Get the record name.
     *
     * @return The record name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the record type.
     *
     * @return The record type.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the TTL value.
     *
     * @return The TTL value.
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * Get the status of the record.
     *
     * @return The status of the record.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the record value.
     *
     * @return The record value.
     */
    public String getValue() {
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
