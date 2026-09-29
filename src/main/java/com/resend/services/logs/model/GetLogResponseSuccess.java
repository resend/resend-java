package com.resend.services.logs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * Represents a successful response for retrieving a single log entry.
 */
public class GetLogResponseSuccess extends Log {

    @JsonProperty("object")
    private @Nullable String object;

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
    public GetLogResponseSuccess(@Nullable String id, @Nullable String createdAt, @Nullable String endpoint, @Nullable String method, @Nullable Integer responseStatus, @Nullable String userAgent, @Nullable Map<String, @Nullable Object> requestBody, @Nullable Map<String, @Nullable Object> responseBody, @Nullable String object) {
        super(id, createdAt, endpoint, method, responseStatus, userAgent, requestBody, responseBody);
        this.object = object;
    }

    /**
     * Gets the object type.
     *
     * @return The object type ("log").
     */
    public @Nullable String getObject() {
        return object;
    }
}
