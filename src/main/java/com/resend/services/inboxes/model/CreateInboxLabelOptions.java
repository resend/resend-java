package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to create a label on an inbox.
 */
public class CreateInboxLabelOptions {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("color")
    private final String color;

    private CreateInboxLabelOptions(Builder builder) {
        this.name = builder.name;
        this.color = builder.color;
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
     * Get the color of the label, as a {@code #RRGGBB} hex color.
     *
     * @return The color of the label, as a {@code #RRGGBB} hex color.
     */
    public String getColor() {
        return color;
    }

    /**
     * Create a new builder instance for constructing CreateInboxLabelOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing CreateInboxLabelOptions objects.
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
         * Set the name of the label. Max 64 characters. Leading and trailing whitespace is trimmed. Must not match another label on the inbox, compared without case.
         *
         * @param name The name of the label.
         * @return The builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the color of the label, as a {@code #RRGGBB} hex color. It is stored in uppercase. When omitted, a random color from the dashboard palette is used.
         *
         * @param color The color of the label, as a {@code #RRGGBB} hex color.
         * @return The builder instance.
         */
        public Builder color(String color) {
            this.color = color;
            return this;
        }

        /**
         * Build a new CreateInboxLabelOptions object.
         *
         * @return A new CreateInboxLabelOptions object.
         * @throws IllegalArgumentException if {@code name} is null or blank.
         */
        public CreateInboxLabelOptions build() {
            if (isBlank(name)) {
                throw new IllegalArgumentException("name must not be null or blank");
            }
            return new CreateInboxLabelOptions(this);
        }

        private static boolean isBlank(String value) {
            if (value == null) {
                return true;
            }
            for (int i = 0; i < value.length(); i++) {
                char c = value.charAt(i);
                if (!Character.isWhitespace(c) && !Character.isSpaceChar(c)) {
                    return false;
                }
            }
            return true;
        }
    }
}
