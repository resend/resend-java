package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents the per-status row counts for a contact import.
 */
public class ContactImportCounts {

    @JsonProperty("total")
    private @Nullable Integer total;

    @JsonProperty("created")
    private @Nullable Integer created;

    @JsonProperty("updated")
    private @Nullable Integer updated;

    @JsonProperty("skipped")
    private @Nullable Integer skipped;

    @JsonProperty("failed")
    private @Nullable Integer failed;

    /**
     * Default constructor.
     */
    public ContactImportCounts() {
    }

    /**
     * Constructs an instance of ContactImportCounts with the specified counts.
     *
     * @param total   The total number of rows processed.
     * @param created The number of contacts created.
     * @param updated The number of contacts updated.
     * @param skipped The number of contacts skipped.
     * @param failed  The number of contacts that failed to import.
     */
    public ContactImportCounts(final @Nullable Integer total, final @Nullable Integer created, final @Nullable Integer updated, final @Nullable Integer skipped, final @Nullable Integer failed) {
        this.total = total;
        this.created = created;
        this.updated = updated;
        this.skipped = skipped;
        this.failed = failed;
    }

    /**
     * Gets the total number of rows processed.
     *
     * @return The total row count.
     */
    public @Nullable Integer getTotal() {
        return total;
    }

    /**
     * Gets the number of contacts created.
     *
     * @return The created count.
     */
    public @Nullable Integer getCreated() {
        return created;
    }

    /**
     * Gets the number of contacts updated.
     *
     * @return The updated count.
     */
    public @Nullable Integer getUpdated() {
        return updated;
    }

    /**
     * Gets the number of contacts skipped.
     *
     * @return The skipped count.
     */
    public @Nullable Integer getSkipped() {
        return skipped;
    }

    /**
     * Gets the number of contacts that failed to import.
     *
     * @return The failed count.
     */
    public @Nullable Integer getFailed() {
        return failed;
    }
}
