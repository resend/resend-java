package com.resend.services.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the per-status row counts for a contact import.
 */
public class ContactImportCounts {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("created")
    private Integer created;

    @JsonProperty("updated")
    private Integer updated;

    @JsonProperty("skipped")
    private Integer skipped;

    @JsonProperty("failed")
    private Integer failed;

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
    public ContactImportCounts(final Integer total, final Integer created, final Integer updated, final Integer skipped, final Integer failed) {
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
    public Integer getTotal() {
        return total;
    }

    /**
     * Gets the number of contacts created.
     *
     * @return The created count.
     */
    public Integer getCreated() {
        return created;
    }

    /**
     * Gets the number of contacts updated.
     *
     * @return The updated count.
     */
    public Integer getUpdated() {
        return updated;
    }

    /**
     * Gets the number of contacts skipped.
     *
     * @return The skipped count.
     */
    public Integer getSkipped() {
        return skipped;
    }

    /**
     * Gets the number of contacts that failed to import.
     *
     * @return The failed count.
     */
    public Integer getFailed() {
        return failed;
    }
}
