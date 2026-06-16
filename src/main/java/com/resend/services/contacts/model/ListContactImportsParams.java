package com.resend.services.contacts.model;

/**
 * Represents the query parameters for listing contact imports.
 *
 * <p>Supports pagination ({@code limit}, {@code after}, {@code before}) and an optional
 * {@code status} filter.</p>
 */
public class ListContactImportsParams {

    private final Integer limit;

    private final String after;

    private final String before;

    private final String status;

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
     * @return The pagination limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Gets the cursor for fetching contact imports created after this ID.
     *
     * @return The {@code after} cursor.
     */
    public String getAfter() {
        return after;
    }

    /**
     * Gets the cursor for fetching contact imports created before this ID.
     *
     * @return The {@code before} cursor.
     */
    public String getBefore() {
        return before;
    }

    /**
     * Gets the status filter ({@code queued}, {@code in_progress}, {@code completed},
     * or {@code failed}).
     *
     * @return The status filter.
     */
    public String getStatus() {
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

        private Integer limit;
        private String after;
        private String before;
        private String status;

        /**
         * Sets the maximum number of contact imports to return (1-100, default 10).
         *
         * @param limit The pagination limit.
         * @return The builder instance.
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the cursor for fetching contact imports after this ID. Cannot be used with
         * {@link #before(String)}.
         *
         * @param after The {@code after} cursor.
         * @return The builder instance.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Sets the cursor for fetching contact imports before this ID. Cannot be used with
         * {@link #after(String)}.
         *
         * @param before The {@code before} cursor.
         * @return The builder instance.
         */
        public Builder before(String before) {
            this.before = before;
            return this;
        }

        /**
         * Filters contact imports by status ({@code queued}, {@code in_progress},
         * {@code completed}, or {@code failed}).
         *
         * @param status The status filter.
         * @return The builder instance.
         */
        public Builder status(String status) {
            this.status = status;
            return this;
        }

        /**
         * Builds a new ListContactImportsParams instance.
         *
         * @return A new ListContactImportsParams instance.
         */
        public ListContactImportsParams build() {
            return new ListContactImportsParams(this);
        }
    }
}
