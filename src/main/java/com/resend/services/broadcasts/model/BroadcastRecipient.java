package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a single recipient row for a broadcast, scoped to the requested event type.
 */
public class BroadcastRecipient {

    @JsonProperty("id")
    private String id;

    @JsonProperty("contact_id")
    private String contactId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("bounce_type")
    private String bounceType;

    @JsonProperty("clicked_links")
    private List<BroadcastRecipientClickedLink> clickedLinks;

    /**
     * Default constructor
     */
    public BroadcastRecipient() {

    }

    /**
     * Constructs a new BroadcastRecipient instance.
     *
     * @param id Opaque cursor identifying this row, used for pagination.
     * @param contactId The ID of the contact associated with this recipient, if one exists.
     * @param email The recipient's email address.
     * @param count The number of times this recipient triggered the event. Only present when
     *              {@code type} is {@code opened} or {@code clicked}.
     * @param bounceType The type of bounce. Only present when {@code type} is {@code bounced}.
     * @param clickedLinks The links this recipient clicked. Only present when {@code type} is
     *                     {@code clicked}.
     */
    public BroadcastRecipient(String id, String contactId, String email, Integer count,
                               String bounceType, List<BroadcastRecipientClickedLink> clickedLinks) {
        this.id = id;
        this.contactId = contactId;
        this.email = email;
        this.count = count;
        this.bounceType = bounceType;
        this.clickedLinks = clickedLinks;
    }

    /**
     * Gets the opaque cursor identifying this row, used for pagination.
     *
     * @return the row cursor
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the ID of the contact associated with this recipient, if one exists.
     *
     * @return the contact ID, or null if none
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Gets the recipient's email address.
     *
     * @return the recipient email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the number of times this recipient triggered the event.
     * Only present when {@code type} is {@code opened} or {@code clicked}.
     *
     * @return the event count, or null if not applicable
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Gets the type of bounce. Only present when {@code type} is {@code bounced}.
     *
     * @return the bounce type, or null if not applicable
     */
    public String getBounceType() {
        return bounceType;
    }

    /**
     * Gets the links this recipient clicked. Only present when {@code type} is {@code clicked}.
     *
     * @return the clicked links, or null if not applicable
     */
    public List<BroadcastRecipientClickedLink> getClickedLinks() {
        return clickedLinks;
    }
}
