package com.resend.services.logs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents a successful response for retrieving a single log entry.
 */
public class GetLogResponseSuccess extends Log {

    @JsonProperty("object")
    private String object;

    /**
     * Default constructor.
     */
    public GetLogResponseSuccess() {
    }

    /**
     * Constructs a GetLogResponseSuccess.
     *
     * @param id             The unique identifier of the log.
     * @param createdAt      The creation timestamp of the log.
     * @param endpoint       The API endpoint that was called.
     * @param method         The HTTP method used.
     * @param responseStatus The HTTP response status code.
     * @param userAgent      The user agent string.
     * @param requestBody    The request body.
     * @param responseBody   The response body.
     * @param object         The object type ("log").
     */
    public GetLogResponseSuccess(String id, String createdAt, String endpoint, String method, Integer responseStatus, String userAgent, Map<String, Object> requestBody, Map<String, Object> responseBody, String object) {
        super(id, createdAt, endpoint, method, responseStatus, userAgent, requestBody, responseBody);
        this.object = object;
    }

    /**
     * Gets the object type.
     *
     * @return The object type ("log").
     */
    public String getObject() {
        return object;
    }
}
