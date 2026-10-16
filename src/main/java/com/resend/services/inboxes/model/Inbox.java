package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an inbox.
 */
public class Inbox {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("domain_id")
    private String domainId;

    @JsonProperty("receiving_address")
    private String receivingAddress;

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("unread")
    private Integer unread;

    @JsonProperty("drafts")
    private Integer drafts;

    @JsonProperty("last_received")
    private String lastReceived;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * Default constructor
     */
    public Inbox() {

    }

    /**
     * Constructs a Inbox.
     *
     * @param object           The object type. Always {@code inbox}.
     * @param id               The ID of the inbox.
     * @param name             The internal name for the inbox. Recipients do not see it. Falls back to the inbox email address.
     * @param emailAddress     The address of the inbox.
     * @param domainId         The ID of the domain that owns the address.
     * @param receivingAddress The address to forward mail to when forwarding is enabled. {@code null} otherwise.
     * @param fromName         The name recipients see when mail is sent from this inbox. {@code null} when not set.
     * @param unread           The number of unread threads in the Inbox folder.
     * @param drafts           The number of unsent drafts.
     * @param lastReceived     The ISO 8601 timestamp of the newest received email in the Inbox folder. {@code null} when the folder has no received email.
     * @param createdAt        The ISO 8601 timestamp when the inbox was created.
     * @param updatedAt        The ISO 8601 timestamp when the inbox was updated.
     */
    public Inbox(String object, String id, String name, String emailAddress, String domainId, String receivingAddress, String fromName, Integer unread, Integer drafts, String lastReceived, String createdAt, String updatedAt) {
        this.object = object;
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.domainId = domainId;
        this.receivingAddress = receivingAddress;
        this.fromName = fromName;
        this.unread = unread;
        this.drafts = drafts;
        this.lastReceived = lastReceived;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Get the object type. Always {@code inbox}.
     *
     * @return The object type. Always {@code inbox}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the inbox.
     *
     * @return The ID of the inbox.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the internal name for the inbox. Recipients do not see it. Falls back to the inbox email address.
     *
     * @return The internal name for the inbox. Recipients do not see it. Falls back to the inbox email address.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the address of the inbox.
     *
     * @return The address of the inbox.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Get the ID of the domain that owns the address.
     *
     * @return The ID of the domain that owns the address.
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * Get the address to forward mail to when forwarding is enabled. {@code null} otherwise.
     *
     * @return The address to forward mail to when forwarding is enabled. {@code null} otherwise.
     */
    public String getReceivingAddress() {
        return receivingAddress;
    }

    /**
     * Get the name recipients see when mail is sent from this inbox. {@code null} when not set.
     *
     * @return The name recipients see when mail is sent from this inbox. {@code null} when not set.
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * Get the number of unread threads in the Inbox folder.
     *
     * @return The number of unread threads in the Inbox folder.
     */
    public Integer getUnread() {
        return unread;
    }

    /**
     * Get the number of unsent drafts.
     *
     * @return The number of unsent drafts.
     */
    public Integer getDrafts() {
        return drafts;
    }

    /**
     * Get the ISO 8601 timestamp of the newest received email in the Inbox folder. {@code null} when the folder has no received email.
     *
     * @return The ISO 8601 timestamp of the newest received email in the Inbox folder. {@code null} when the folder has no received email.
     */
    public String getLastReceived() {
        return lastReceived;
    }

    /**
     * Get the ISO 8601 timestamp when the inbox was created.
     *
     * @return The ISO 8601 timestamp when the inbox was created.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the ISO 8601 timestamp when the inbox was updated.
     *
     * @return The ISO 8601 timestamp when the inbox was updated.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }
}
