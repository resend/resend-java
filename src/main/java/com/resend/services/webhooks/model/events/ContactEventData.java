package com.resend.services.webhooks.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Data payload for contact webhook events.
 */
public class ContactEventData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("audience_id")
    private String audienceId;

    @JsonProperty("segment_ids")
    private List<String> segmentIds;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("unsubscribed")
    private Boolean unsubscribed;

    /**
     * Default constructor.
     */
    public ContactEventData() {
    }

    /**
     * Gets the contact ID.
     *
     * @return The contact ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the contact ID.
     *
     * @param id The contact ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the audience ID.
     *
     * @return The audience ID.
     */
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Sets the audience ID.
     *
     * @param audienceId The audience ID.
     */
    public void setAudienceId(String audienceId) {
        this.audienceId = audienceId;
    }

    /**
     * Gets the segment IDs.
     *
     * @return The segment IDs.
     */
    public List<String> getSegmentIds() {
        return segmentIds;
    }

    /**
     * Sets the segment IDs.
     *
     * @param segmentIds The segment IDs.
     */
    public void setSegmentIds(List<String> segmentIds) {
        this.segmentIds = segmentIds;
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
     * Gets the update timestamp.
     *
     * @return The update timestamp.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update timestamp.
     *
     * @param updatedAt The update timestamp.
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the contact email address.
     *
     * @return The contact email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact email address.
     *
     * @param email The contact email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the contact first name.
     *
     * @return The contact first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the contact first name.
     *
     * @param firstName The contact first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the contact last name.
     *
     * @return The contact last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the contact last name.
     *
     * @param lastName The contact last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets whether the contact is unsubscribed.
     *
     * @return Whether the contact is unsubscribed.
     */
    public Boolean getUnsubscribed() {
        return unsubscribed;
    }

    /**
     * Sets whether the contact is unsubscribed.
     *
     * @param unsubscribed Whether the contact is unsubscribed.
     */
    public void setUnsubscribed(Boolean unsubscribed) {
        this.unsubscribed = unsubscribed;
    }
}
