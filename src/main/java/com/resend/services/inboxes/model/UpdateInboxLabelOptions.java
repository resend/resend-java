package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to update an inbox label. Omitted fields keep their current value.
 */
public class UpdateInboxLabelOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("color")
    private final String color;

    private UpdateInboxLabelOptions(Builder builder) {
        this.name = builder.name;
        this.color = builder.color;
    }

    /**
     * Get the new name for the label.
     *
     * @return The new name for the label.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the new color for the label, as a {@code #RRGGBB} hex color.
     *
     * @return The new color for the label, as a {@code #RRGGBB} hex color.
     */
    public String getColor() {
        return color;
    }

    /**
     * Create a new builder instance for constructing UpdateInboxLabelOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateInboxLabelOptions objects.
     */
    public static class Builder {
        private String name;
        private String color;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the new name for the label. Max 64 characters. Leading and trailing whitespace is trimmed. Must not match another label on the inbox, compared without case.
         *
         * @param name The new name for the label.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the new color for the label, as a {@code #RRGGBB} hex color. It is stored in uppercase.
         *
         * @param color The new color for the label, as a {@code #RRGGBB} hex color.
         * @return The builder instance.
         */
        public Builder color(String color) {
            this.color = color;
            return this;
        }

        /**
         * Build a new UpdateInboxLabelOptions object.
         *
         * @return A new UpdateInboxLabelOptions object.
         */
        public UpdateInboxLabelOptions build() {
            return new UpdateInboxLabelOptions(this);
        }
    }
}
