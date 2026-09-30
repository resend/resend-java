package com.resend.services.contacts.model;

import org.jspecify.annotations.Nullable;


/**
 * Represents the query parameters for listing contact imports.
 *
 * <p>Supports pagination ({@code limit}, {@code after}, {@code before}) and an optional
 * {@code status} filter.</p>
 */
public class ListContactImportsParams {

    private final @Nullable Integer limit;

    private final @Nullable String after;

    private final @Nullable String before;

    private final @Nullable String status;

    /**
     * Constructs a ListContactImportsParams object using the provided builder.
     *
     * @param builder The builder to construct the params.
     */
    public ListContactImportsParams(Builder builder) {
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
        this.status = builder.status;
    }

    /**
     * Gets the pagination limit.
     *
     * @return The pagination limit, or {@code null} if not set.
     */
    public @Nullable Integer getLimit() {
        return limit;
    }

    /**
     * Gets the cursor for fetching contact imports created after this ID.
     *
     * @return The {@code after} cursor, or {@code null} if not set.
     */
    public @Nullable String getAfter() {
        return after;
    }

    /**
     * Gets the cursor for fetching contact imports created before this ID.
     *
     * @return The {@code before} cursor, or {@code null} if not set.
     */
    public @Nullable String getBefore() {
        return before;
    }

    /**
     * Gets the status filter ({@code queued}, {@code in_progress}, {@code completed},
     * or {@code failed}).
     *
     * @return The status filter, or {@code null} if not set.
     */
    public @Nullable String getStatus() {
        return status;
    }

    /**
     * Creates a new builder instance for constructing ListContactImportsParams objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListContactImportsParams objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private @Nullable Integer limit;
        private @Nullable String after;
        private @Nullable String before;
        private @Nullable String status;

        /**
         * Sets the maximum number of contact imports to return (1-100, default 10).
         *
         * @param limit The pagination limit, or {@code null} to leave it unset.
         * @return The builder instance.
         */
        public Builder limit(@Nullable Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the cursor for fetching contact imports after this ID. Cannot be used with
         * {@link #before(String)}.
         *
         * @param after The {@code after} cursor, or {@code null} to leave it unset.
         * @return The builder instance.
         */
        public Builder after(@Nullable String after) {
            this.after = after;
            return this;
        }

        /**
         * Sets the cursor for fetching contact imports before this ID. Cannot be used with
         * {@link #after(String)}.
         *
         * @param before The {@code before} cursor, or {@code null} to leave it unset.
         * @return The builder instance.
         */
        public Builder before(@Nullable String before) {
            this.before = before;
            return this;
        }

        /**
         * Filters contact imports by status ({@code queued}, {@code in_progress},
         * {@code completed}, or {@code failed}).
         *
         * @param status The status filter, or {@code null} to leave it unset.
         * @return The builder instance.
         */
        public Builder status(@Nullable String status) {
            this.status = status;
            return this;
        }

        /**
         * Builds a new ListContactImportsParams instance.
         *
         * @return A new ListContactImportsParams instance.
         * @throws IllegalArgumentException if both {@code after} and {@code before} are set.
         */
        public ListContactImportsParams build() {
            boolean hasAfter = after != null && !after.isEmpty();
            boolean hasBefore = before != null && !before.isEmpty();
            if (hasAfter && hasBefore) {
                throw new IllegalArgumentException(
                        "after and before cannot be used together");
            }
            return new ListContactImportsParams(this);
        }
    }
}
