package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.domains.model.Record;
import java.util.List;

/**
 * Data payload for domain webhook events.
 */
public class DomainEventData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("region")
    private String region;

    @JsonProperty("records")
    private List<Record> records;

    /**
     * Default constructor.
     */
    public DomainEventData() {
    }

    /**
     * Gets the domain ID.
     *
     * @return The domain ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the domain ID.
     *
     * @param id The domain ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the domain name.
     *
     * @return The domain name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the domain name.
     *
     * @param name The domain name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the domain status.
     *
     * @return The domain status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the domain status.
     *
     * @param status The domain status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the domain region.
     *
     * @return The domain region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the domain region.
     *
     * @param region The domain region.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets the domain DNS records.
     *
     * @return The domain DNS records.
     */
    public List<Record> getRecords() {
        return records;
    }

    /**
     * Sets the domain DNS records.
     *
     * @param records The domain DNS records.
     */
    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
