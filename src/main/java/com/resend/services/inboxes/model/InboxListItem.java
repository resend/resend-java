package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an inbox in a list of inboxes. The list items carry fewer fields than a single inbox.
 */
public class InboxListItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("unread")
    private Integer unread;

    @JsonProperty("last_received")
    private String lastReceived;

    /**
     * Default constructor
     */
    public InboxListItem() {

    }

    /**
     * Constructs a InboxListItem.
     *
     * @param id           The ID of the inbox.
     * @param name         The internal name for the inbox. Recipients do not see it.
     * @param emailAddress The address of the inbox.
     * @param fromName     The name recipients see when mail is sent from this inbox. {@code null} when not set.
     * @param unread       The number of unread threads in the Inbox folder.
     * @param lastReceived The ISO 8601 timestamp of the newest received email in the Inbox folder. {@code null} when the folder has no received email.
     */
    public InboxListItem(String id, String name, String emailAddress, String fromName, Integer unread, String lastReceived) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.fromName = fromName;
        this.unread = unread;
        this.lastReceived = lastReceived;
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
     * Get the internal name for the inbox. Recipients do not see it.
     *
     * @return The internal name for the inbox. Recipients do not see it.
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
     * Get the ISO 8601 timestamp of the newest received email in the Inbox folder. {@code null} when the folder has no received email.
     *
     * @return The ISO 8601 timestamp of the newest received email in the Inbox folder. {@code null} when the folder has no received email.
     */
    public String getLastReceived() {
        return lastReceived;
    }
}
