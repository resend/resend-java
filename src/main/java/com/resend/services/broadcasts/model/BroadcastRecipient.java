package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Represents a single recipient row for a broadcast, scoped to the requested event type.
 */
public class BroadcastRecipient {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("contact_id")
    private @Nullable String contactId;

    @JsonProperty("email")
    private @Nullable String email;

    @JsonProperty("count")
    private @Nullable Integer count;

    @JsonProperty("bounce_type")
    private @Nullable String bounceType;

    @JsonProperty("clicked_links")
    private @Nullable List<BroadcastRecipientClickedLink> clickedLinks;

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
    public BroadcastRecipient(@Nullable String id, @Nullable String contactId, @Nullable String email, @Nullable Integer count,
                               @Nullable String bounceType, @Nullable List<BroadcastRecipientClickedLink> clickedLinks) {
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
    public @Nullable String getId() {
        return id;
    }

    /**
     * Gets the ID of the contact associated with this recipient, if one exists.
     *
     * @return the contact ID, or null if none
     */
    public @Nullable String getContactId() {
        return contactId;
    }

    /**
     * Gets the recipient's email address.
     *
     * @return the recipient email
     */
    public @Nullable String getEmail() {
        return email;
    }

    /**
     * Gets the number of times this recipient triggered the event.
     * Only present when {@code type} is {@code opened} or {@code clicked}.
     *
     * @return the event count, or null if not applicable
     */
    public @Nullable Integer getCount() {
        return count;
    }

    /**
     * Gets the type of bounce. Only present when {@code type} is {@code bounced}.
     *
     * @return the bounce type, or null if not applicable
     */
    public @Nullable String getBounceType() {
        return bounceType;
    }

    /**
     * Gets the links this recipient clicked. Only present when {@code type} is {@code clicked}.
     *
     * @return the clicked links, or null if not applicable
     */
    public @Nullable List<BroadcastRecipientClickedLink> getClickedLinks() {
        return clickedLinks;
    }
}
