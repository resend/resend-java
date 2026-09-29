package com.resend.services.logs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * Represents a log entry for a single API request.
 */
public class Log {

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

    @JsonProperty("request_body")
    private @Nullable Map<String, @Nullable Object> requestBody;

    @JsonProperty("response_body")
    private @Nullable Map<String, @Nullable Object> responseBody;

    /**
     * Default constructor.
     */
    public Log() {
    }

    /**
     * Constructs a Log entry.
     *
     * @param id             The unique identifier of the log.
     * @param createdAt      The creation timestamp of the log.
     * @param endpoint       The API endpoint that was called.
     * @param method         The HTTP method used.
     * @param responseStatus The HTTP response status code.
     * @param userAgent      The user agent string.
     * @param requestBody    The request body.
     * @param responseBody   The response body.
     */
    public Log(@Nullable String id, @Nullable String createdAt, @Nullable String endpoint, @Nullable String method, @Nullable Integer responseStatus, @Nullable String userAgent, @Nullable Map<String, @Nullable Object> requestBody, @Nullable Map<String, @Nullable Object> responseBody) {
        this.id = id;
        this.createdAt = createdAt;
        this.endpoint = endpoint;
        this.method = method;
        this.responseStatus = responseStatus;
        this.userAgent = userAgent;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
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

    /**
     * Gets the request body.
     *
     * @return the request body as a map
     */
    public @Nullable Map<String, @Nullable Object> getRequestBody() { return requestBody; }

    /**
     * Gets the response body.
     *
     * @return the response body as a map
     */
    public @Nullable Map<String, @Nullable Object> getResponseBody() { return responseBody; }
}
