package com.resend.services.broadcasts.model;

import com.resend.core.helper.URLHelper;
import com.resend.core.net.ListParams;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the query parameters for listing a broadcast's recipients.
 *
 * <p>Supports pagination ({@code limit}, {@code after}, {@code before}), a required
 * {@code type} filter, an optional {@code email} substring filter, and an optional
 * {@code bounceType} filter (only meaningful when {@code type} is {@code bounced}).</p>
 */
public class ListBroadcastRecipientsParams {

    private final BroadcastRecipientEventType type;

    private final String email;

    private final BroadcastRecipientBounceType bounceType;

    private final Integer limit;

    private final String after;

    private final String before;

    /**
     * Constructs a ListBroadcastRecipientsParams object using the provided builder.
     *
     * @param builder The builder to construct the params.
     */
    public ListBroadcastRecipientsParams(Builder builder) {
        this.type = builder.type;
        this.email = builder.email;
        this.bounceType = builder.bounceType;
        this.limit = builder.limit;
        this.after = builder.after;
        this.before = builder.before;
    }

    /**
     * Gets the event type filter.
     *
     * @return The recipient event type.
     */
    public BroadcastRecipientEventType getType() {
        return type;
    }

    /**
     * Gets the email substring filter.
     *
     * @return The email filter.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the bounce type filter.
     *
     * @return The bounce type filter.
     */
    public BroadcastRecipientBounceType getBounceType() {
        return bounceType;
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
     * Gets the cursor for pagination (after).
     *
     * @return The after cursor.
     */
    public String getAfter() {
        return after;
    }

    /**
     * Gets the cursor for pagination (before).
     *
     * @return The before cursor.
     */
    public String getBefore() {
        return before;
    }

    /**
     * Converts the parameters to a query string.
     *
     * @return A query string starting with "?" if parameters exist, or an empty string otherwise.
     */
    public String toQueryString() {
        ListParams base = ListParams.builder()
                .limit(limit)
                .after(after)
                .before(before)
                .build();

        Map<String, String> extras = new LinkedHashMap<>();
        extras.put("type", type.getValue());
        extras.put("email", email);
        extras.put("bounce_type", bounceType != null ? bounceType.getValue() : null);

        return URLHelper.parse(base, extras);
    }

    /**
     * Creates a new builder instance for constructing ListBroadcastRecipientsParams objects.
     *
     * @return A new builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ListBroadcastRecipientsParams objects.
     */
    public static class Builder {
        /**
         * Creates a new Builder instance.
         */
        public Builder() {
        }

        private BroadcastRecipientEventType type;
        private String email;
        private BroadcastRecipientBounceType bounceType;
        private Integer limit;
        private String after;
        private String before;

        /**
         * Sets the event type to filter recipients by.
         *
         * @param type The recipient event type.
         * @return The builder instance.
         */
        public Builder type(BroadcastRecipientEventType type) {
            this.type = type;
            return this;
        }

        /**
         * Filters recipients by a substring of their email address.
         *
         * @param email The email substring filter.
         * @return The builder instance.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Filters bounced recipients by bounce type. Only meaningful when {@code type} is
         * {@link BroadcastRecipientEventType#BOUNCED}.
         *
         * @param bounceType The bounce type filter.
         * @return The builder instance.
         */
        public Builder bounceType(BroadcastRecipientBounceType bounceType) {
            this.bounceType = bounceType;
            return this;
        }

        /**
         * Sets the maximum number of recipients to return (1-100, default 20).
         *
         * @param limit The pagination limit.
         * @return The builder instance.
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the cursor for fetching recipients after this cursor. Cannot be used with
         * {@link #before(String)}.
         *
         * @param after The after cursor.
         * @return The builder instance.
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Sets the cursor for fetching recipients before this cursor. Cannot be used with
         * {@link #after(String)}.
         *
         * @param before The before cursor.
         * @return The builder instance.
         */
        public Builder before(String before) {
            this.before = before;
            return this;
        }

        /**
         * Builds a new ListBroadcastRecipientsParams instance.
         *
         * @return A new ListBroadcastRecipientsParams instance.
         * @throws IllegalArgumentException if {@code type} is not set, if both {@code after}
         *         and {@code before} are set, or if {@code bounceType} is set while
         *         {@code type} is not {@code BOUNCED}.
         */
        public ListBroadcastRecipientsParams build() {
            if (type == null) {
                throw new IllegalArgumentException("type must be provided.");
            }
            boolean hasAfter = after != null && !after.isEmpty();
            boolean hasBefore = before != null && !before.isEmpty();
            if (hasAfter && hasBefore) {
                throw new IllegalArgumentException("after and before cannot be used together");
            }
            if (bounceType != null && type != BroadcastRecipientEventType.BOUNCED) {
                throw new IllegalArgumentException("bounceType can only be used when type is BOUNCED");
            }
            return new ListBroadcastRecipientsParams(this);
        }
    }
}
