package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Represents a thread that matched a search.
 * Extends the InboxThread class.
 */
public class InboxThreadSearchResult extends InboxThread {

    @JsonProperty("matched_email_id")
    private String matchedEmailId;

    @JsonProperty("highlights")
    private Map<String, List<String>> highlights;

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
     * @param matchedEmailId The email in the thread that matched the search. The get, reply and forward thread email endpoints accept it as {@code email_id}, but it is not equal to the {@code id} those endpoints return. Null when the search index returns no matched email for the thread.
     * @param highlights     Where {@code query} matched the email in {@code matched_email_id}, as text fragments with each match wrapped in {@code **}, keyed by {@code subject}, {@code body}, {@code from}, {@code to}, {@code cc}, {@code bcc} or {@code attachments}. Only keys with a match are present. Empty when there is no {@code query}.
     */
    public InboxThreadSearchResult(String id, String subject, String from, List<String> to, List<String> cc, List<String> bcc, List<InboxThreadLabel> labels, Integer emailCount, Boolean hasAttachment, Boolean hasDraft, Boolean read, String receivedAt, String folder, String matchedEmailId, Map<String, List<String>> highlights) {
        super(id, subject, from, to, cc, bcc, labels, emailCount, hasAttachment, hasDraft, read, receivedAt, folder);
        this.matchedEmailId = matchedEmailId;
        this.highlights = highlights;
    }

    /**
     * Get the email in the thread that matched the search. The get, reply and forward thread email endpoints accept it as {@code email_id}, but it is not equal to the {@code id} those endpoints return. Null when the search index returns no matched email for the thread.
     *
     * @return The email in the thread that matched the search. The get, reply and forward thread email endpoints accept it as {@code email_id}, but it is not equal to the {@code id} those endpoints return. Null when the search index returns no matched email for the thread.
     */
    public String getMatchedEmailId() {
        return matchedEmailId;
    }

    /**
     * Get the places where {@code query} matched the email in {@code matched_email_id}, as text fragments with each match wrapped in {@code **}, keyed by {@code subject}, {@code body}, {@code from}, {@code to}, {@code cc}, {@code bcc} or {@code attachments}. Only keys with a match are present. Empty when there is no {@code query}.
     *
     * @return Where {@code query} matched the email in {@code matched_email_id}, as text fragments with each match wrapped in {@code **}, keyed by {@code subject}, {@code body}, {@code from}, {@code to}, {@code cc}, {@code bcc} or {@code attachments}. Only keys with a match are present. Empty when there is no {@code query}.
     */
    public Map<String, List<String>> getHighlights() {
        return highlights;
    }
}
