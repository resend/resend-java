package com.resend.services.inboxes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a request to set the labels of a thread. The thread ends up with exactly the given labels.
 */
public class SetInboxThreadLabelsOptions {

    @JsonProperty("labels")
    private final List<String> labels;

    private SetInboxThreadLabelsOptions(Builder builder) {
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
     * Create a new builder instance for constructing SetInboxThreadLabelsOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing SetInboxThreadLabelsOptions objects.
     */
    public static class Builder {
        private List<String> labels;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        /**
         * Set the IDs of the labels. Up to 100 IDs, each belonging to the inbox. An empty list takes every label off the thread.
         *
         * @param labels The IDs of the labels.
         * @return The builder instance.
         */
        public Builder labels(String... labels) {
            this.labels = new ArrayList<>(Arrays.asList(labels));
            return this;
        }

        /**
         * Set the IDs of the labels. Up to 100 IDs, each belonging to the inbox. An empty list takes every label off the thread.
         *
         * @param labels The IDs of the labels.
         * @return The builder instance.
         */
        public Builder labels(List<String> labels) {
            this.labels = labels == null ? null : new ArrayList<>(labels);
            return this;
        }

        /**
         * Build a new SetInboxThreadLabelsOptions object.
         *
         * @return A new SetInboxThreadLabelsOptions object.
         * @throws IllegalArgumentException if {@code labels} is null.
         */
        public SetInboxThreadLabelsOptions build() {
            if (labels == null) {
                throw new IllegalArgumentException("labels must not be null");
            }
            return new SetInboxThreadLabelsOptions(this);
        }
    }
}
