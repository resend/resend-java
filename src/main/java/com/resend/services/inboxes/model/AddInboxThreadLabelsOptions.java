package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a request to add labels to a thread. Labels already on the thread stay on it.
 */
public class AddInboxThreadLabelsOptions {

    @JsonProperty("labels")
    private final List<String> labels;

    private AddInboxThreadLabelsOptions(Builder builder) {
        this.labels = new ArrayList<>(builder.labels);
    }

    /**
     * Get the IDs of the labels.
     *
     * @return The IDs of the labels.
     */
    public List<String> getLabels() {
        return labels;
    }

    /**
     * Create a new builder instance for constructing AddInboxThreadLabelsOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing AddInboxThreadLabelsOptions objects.
     */
    public static class Builder {
        private List<String> labels;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the IDs of the labels. Between 1 and 10 IDs, each belonging to the inbox.
         *
         * @param labels The IDs of the labels.
         * @return The builder instance.
         */
        public Builder labels(String... labels) {
            this.labels = new ArrayList<>(Arrays.asList(labels));
            return this;
        }

        /**
         * Set the IDs of the labels. Between 1 and 10 IDs, each belonging to the inbox.
         *
         * @param labels The IDs of the labels.
         * @return The builder instance.
         */
        public Builder labels(List<String> labels) {
            this.labels = labels == null ? null : new ArrayList<>(labels);
            return this;
        }

        /**
         * Build a new AddInboxThreadLabelsOptions object.
         *
         * @return A new AddInboxThreadLabelsOptions object.
         * @throws IllegalArgumentException if {@code labels} is null or empty, or contains a null or blank ID.
         */
        public AddInboxThreadLabelsOptions build() {
            if (labels == null || labels.isEmpty()) {
                throw new IllegalArgumentException("labels must contain at least one label ID");
            }
            if (hasBlankId(labels)) {
                throw new IllegalArgumentException("labels must not contain a null or blank label ID");
            }
            return new AddInboxThreadLabelsOptions(this);
        }

        private static boolean hasBlankId(List<String> labels) {
            for (String label : labels) {
                if (label == null || label.trim().isEmpty()) {
                    return true;
                }
            }
            return false;
        }
    }
}
