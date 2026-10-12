package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a label attached to a thread.
 */
public class InboxThreadLabel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("color")
    private String color;

    /**
     * Default constructor
     */
    public InboxThreadLabel() {

    }

    /**
     * Constructs a InboxThreadLabel.
     *
     * @param id    The ID of the label.
     * @param name  The name of the label.
     * @param color The color of the label, as a {@code #RRGGBB} hex color in uppercase.
     */
    public InboxThreadLabel(String id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
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
}
