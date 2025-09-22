package com.resend.services.domains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resend.services.domains.dto.DomainDTO;

import java.util.List;

/**
 * Represents a response object for listing domains. This class contains a list of DomainDTO objects
 * and is used to encapsulate the data returned from a list domains operation.
 */
public class ListDomainsResponse {
    /**
     * The list of DomainDTO objects containing domain data.
     */
    @JsonProperty("data")
    public List<DomainDTO> data;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor for creating an empty ListDomainsResponse object.
     */
    public ListDomainsResponse() {
    }

    /**
     * Constructor to create a ListDomainsResponse object with the provided list of DomainDTO objects.
     *
     * @param data The list of DomainDTO objects.
     * @param hasMore Indicate if there are more items to be returned.
     * @param object the object type of the module.
     */
    public ListDomainsResponse(List<DomainDTO> data, Boolean hasMore, String object) {
        this.data = data;
        this.hasMore = hasMore;
        this.object = object;
    }

    /**
     * Get the list of DomainDTO objects containing domain data.
     *
     * @return The list of DomainDTO objects.
     */
    public List<DomainDTO> getData() {
        return data;
    }

    /**
     * Gets the indicator whether there are more items available for pagination.
     *
     * @return Whether there are more items available for pagination.
     */
    public Boolean hasMore() {
        return hasMore;
    }

    /**
     * Get the type of the object.
     *
     * @return The type of the object.
     */
    public String getObject() {
        return object;
    }
}

