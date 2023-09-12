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

    /**
     * Default constructor for creating an empty ListDomainsResponse object.
     */
    public ListDomainsResponse() {
    }

    /**
     * Constructor to create a ListDomainsResponse object with the provided list of DomainDTO objects.
     *
     * @param data The list of DomainDTO objects.
     */
    public ListDomainsResponse(List<DomainDTO> data) {
        this.data = data;
    }

    /**
     * Get the list of DomainDTO objects containing domain data.
     *
     * @return The list of DomainDTO objects.
     */
    public List<DomainDTO> getData() {
        return data;
    }
}

