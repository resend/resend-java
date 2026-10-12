package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the summary of a single thread.
 */
public class InboxThreadSummary {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("folder")
    private String folder;

    @JsonProperty("labels")
    private List<InboxThreadLabel> labels;

    @JsonProperty("read")
    private Boolean read;

    /**
     * Default constructor
     */
    public InboxThreadSummary() {

    }

    /**
     * Constructs a InboxThreadSummary.
     *
     * @param object  The object type. Always {@code inbox_thread}.
     * @param id      The ID of the thread.
     * @param subject The subject of the thread.
     * @param folder  The folder the thread is in.
     * @param labels  The labels attached to the thread.
     * @param read    True only when every email in the thread is read.
     */
    public InboxThreadSummary(String object, String id, String subject, String folder, List<InboxThreadLabel> labels, Boolean read) {
        this.object = object;
        this.id = id;
        this.subject = subject;
        this.folder = folder;
        this.labels = labels;
        this.read = read;
    }

    /**
     * Get the object type. Always {@code inbox_thread}.
     *
     * @return The object type. Always {@code inbox_thread}.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the ID of the thread.
     *
     * @return The ID of the thread.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the subject of the thread.
     *
     * @return The subject of the thread.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the folder the thread is in.
     *
     * @return The folder the thread is in.
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Get the labels attached to the thread.
     *
     * @return The labels attached to the thread.
     */
    public List<InboxThreadLabel> getLabels() {
        return labels;
    }

    /**
     * Get true only when every email in the thread is read.
     *
     * @return True only when every email in the thread is read.
     */
    public Boolean getRead() {
        return read;
    }
}
