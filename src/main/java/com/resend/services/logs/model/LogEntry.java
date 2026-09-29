package com.resend.services.logs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

/**
 * Represents a summary log entry returned in the list logs response.
 * Contains a subset of fields compared to the full {@link Log} object.
 */
public class LogEntry {

    @JsonProperty("id")
    private @Nullable String id;

    @JsonProperty("created_at")
    private @Nullable String createdAt;

    @JsonProperty("endpoint")
    private @Nullable String endpoint;

    @JsonProperty("method")
    private @Nullable String method;

    @JsonProperty("response_status")
    private @Nullable Integer responseStatus;

    @JsonProperty("user_agent")
    private @Nullable String userAgent;

    /**
     * Default constructor.
     */
    public LogEntry() {
    }

    /**
     * Constructs a LogEntry.
     *
     * @param id             The unique identifier of the log.
     * @param createdAt      The creation timestamp of the log.
     * @param endpoint       The API endpoint that was called.
     * @param method         The HTTP method used.
     * @param responseStatus The HTTP response status code.
     * @param userAgent      The user agent string.
     */
    public LogEntry(@Nullable String id, @Nullable String createdAt, @Nullable String endpoint, @Nullable String method, @Nullable Integer responseStatus, @Nullable String userAgent) {
        this.id = id;
        this.createdAt = createdAt;
        this.endpoint = endpoint;
        this.method = method;
        this.responseStatus = responseStatus;
        this.userAgent = userAgent;
    }

    /**
     * Gets the unique identifier of the log.
     *
     * @return the log ID
     */
    public @Nullable String getId() { return id; }

    /**
     * Gets the creation timestamp of the log.
     *
     * @return the creation timestamp
     */
    public @Nullable String getCreatedAt() { return createdAt; }

    /**
     * Gets the API endpoint that was called.
     *
     * @return the endpoint path
     */
    public @Nullable String getEndpoint() { return endpoint; }

    /**
     * Gets the HTTP method used.
     *
     * @return the HTTP method
     */
    public @Nullable String getMethod() { return method; }

    /**
     * Gets the HTTP response status code.
     *
     * @return the response status code
     */
    public @Nullable Integer getResponseStatus() { return responseStatus; }

    /**
     * Gets the user agent string.
     *
     * @return the user agent
     */
    public @Nullable String getUserAgent() { return userAgent; }
}
