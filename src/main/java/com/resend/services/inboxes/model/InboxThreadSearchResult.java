package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a thread that matched a search.
 * Extends the InboxThread class.
 */
public class InboxThreadSearchResult extends InboxThread {

    @JsonProperty("matched_email_id")
    private String matchedEmailId;

    /**
     * Default constructor
     */
    public InboxThreadSearchResult() {

    }

    /**
     * Constructs a InboxThreadSearchResult.
     *
     * @param id             The ID of the thread.
     * @param subject        The subject of the thread.
     * @param from           The sender email address. Can include the display name.
     * @param to             The recipients of the most recent email. For a received email, these are the addresses it was delivered to, not the full {@code To} header.
     * @param cc             The CC recipients of the most recent email.
     * @param bcc            The BCC recipients of the most recent email. Always empty for a received email.
     * @param labels         The labels attached to the thread.
     * @param emailCount   The number of emails in the thread.
     * @param hasAttachment  Whether the most recent email in the thread has an attachment.
     * @param hasDraft       Whether the thread has an unsent draft.
     * @param read           True only when every email in the thread is read.
     * @param receivedAt     The timestamp when the thread was last active.
     * @param folder         The folder the thread is in. Every thread is in exactly one folder.
     * @param matchedEmailId The ID of the email in the thread that matched the search. When {@code query} has words or phrases, it is the most relevant match. Otherwise, it is the newest. It is the same {@code id} that the list thread emails endpoint returns, so use it as {@code email_id} with the get, reply and forward endpoints. Null when the search index returns no matched email for the thread.
     */
    public InboxThreadSearchResult(String id, String subject, String from, List<String> to, List<String> cc, List<String> bcc, List<InboxThreadLabel> labels, Integer emailCount, Boolean hasAttachment, Boolean hasDraft, Boolean read, String receivedAt, String folder, String matchedEmailId) {
        super(id, subject, from, to, cc, bcc, labels, emailCount, hasAttachment, hasDraft, read, receivedAt, folder);
        this.matchedEmailId = matchedEmailId;
    }

    /**
     * Get the ID of the email in the thread that matched the search. When {@code query} has words or phrases, it is the most relevant match. Otherwise, it is the newest. It is the same {@code id} that the list thread emails endpoint returns, so use it as {@code email_id} with the get, reply and forward endpoints. Null when the search index returns no matched email for the thread.
     *
     * @return The ID of the email in the thread that matched the search. When {@code query} has words or phrases, it is the most relevant match. Otherwise, it is the newest. It is the same {@code id} that the list thread emails endpoint returns, so use it as {@code email_id} with the get, reply and forward endpoints. Null when the search index returns no matched email for the thread.
     */
    public String getMatchedEmailId() {
        return matchedEmailId;
    }
}
