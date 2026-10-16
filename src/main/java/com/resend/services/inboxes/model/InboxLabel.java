package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a label on an inbox.
 */
public class InboxLabel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("color")
    private String color;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Default constructor
     */
    public InboxLabel() {

    }

    /**
     * Constructs a InboxLabel.
     *
     * @param id        The ID of the label.
     * @param name      The name of the label.
     * @param color     The color of the label, as a {@code #RRGGBB} hex color in uppercase.
     * @param createdAt The timestamp when the label was created.
     */
    public InboxLabel(String id, String name, String color, String createdAt) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.createdAt = createdAt;
    }

    /**
     * Get the ID of the label.
     *
     * @return The ID of the label.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the name of the label.
     *
     * @return The name of the label.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the color of the label, as a {@code #RRGGBB} hex color in uppercase.
     *
     * @return The color of the label, as a {@code #RRGGBB} hex color in uppercase.
     */
    public String getColor() {
        return color;
    }

    /**
     * Get the timestamp when the label was created.
     *
     * @return The timestamp when the label was created.
     */
    public String getCreatedAt() {
        return createdAt;
    }
}
