package com.resend.services.inboxes.model;

/**
 * Represents a successful response for retrieving an inbox.
 * Extends the Inbox class.
 */
public class GetInboxResponseSuccess extends Inbox {

    /**
     * Default constructor
     */
    public GetInboxResponseSuccess() {

    }

    /**
     * Constructs a GetInboxResponseSuccess.
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
     */
    public GetInboxResponseSuccess(String object, String id, String name, String emailAddress, String domainId, String receivingAddress, String fromName, Integer unread, Integer drafts, String lastReceived, String createdAt) {
        super(object, id, name, emailAddress, domainId, receivingAddress, fromName, unread, drafts, lastReceived, createdAt);
    }
}
