package com.resend.services.broadcasts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Class representing options to update a broadcast.
 */
public class UpdateBroadcastOptions extends BroadcastOptions {

    private final @Nullable String id;

    @JsonProperty("preview_text")
    private final @Nullable String previewText;

    /**
     * Constructs an UpdateBroadcastOptions object using the provided builder.
     *
     * @param builder The builder to construct the UpdateBroadcastOptions.
     */
    public UpdateBroadcastOptions(Builder builder) {
        super(builder);
        this.id = builder.id;
        this.previewText = builder.previewText;
    }

    /**
     * Get the ID of the broadcast.
     *
     * @return The broadcast ID.
     */
    public @Nullable String getId() {
        return id;
    }

    /**
     * Get the preview text of the broadcast.
     *
     * @return The broadcast preview text.
     */
    public @Nullable String getPreviewText() {
        return previewText;
    }

    /**
     * Create a new builder instance for constructing UpdateBroadcastOptions objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing UpdateBroadcastOptions objects.
     */
    public static class Builder extends BroadcastOptions.Builder<UpdateBroadcastOptions, Builder> {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable String id;

        private @Nullable String previewText;

        /**
         * Set the ID of the broadcast.
         *
         * @param id The broadcast ID.
         * @return The builder instance.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Set the preview text of the broadcast.
         *
         * @param previewText The broadcast preview text.
         * @return The builder instance.
         */
        public Builder previewText(@Nullable String previewText) {
            this.previewText = previewText;
            return this;
        }

        /**
         * Build a new UpdateBroadcastOptions object.
         *
         * @return A new UpdateBroadcastOptions object.
         */
        @Override
        public UpdateBroadcastOptions build() {
            return new UpdateBroadcastOptions(this);
        }

        /**
         * Return the builder instance.
         *
         * @return The builder instance.
         */
        @Override
        protected Builder self() {
            return this;
        }
    }
}