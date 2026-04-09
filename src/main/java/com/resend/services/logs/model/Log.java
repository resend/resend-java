package com.resend.services.logs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents a log entry for a single API request.
 */
public class Log {

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("method")
    private String method;

    @JsonProperty("response_status")
    private Integer responseStatus;

    @JsonProperty("user_agent")
    private String userAgent;

    @JsonProperty("request_body")
    private Map<String, Object> requestBody;

    @JsonProperty("response_body")
    private Map<String, Object> responseBody;

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
    public Log(String id, String createdAt, String endpoint, String method, Integer responseStatus, String userAgent, Map<String, Object> requestBody, Map<String, Object> responseBody) {
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
    public String getId() { return id; }

    /**
     * Gets the creation timestamp of the log.
     *
     * @return the creation timestamp
     */
    public String getCreatedAt() { return createdAt; }

    /**
     * Gets the API endpoint that was called.
     *
     * @return the endpoint path
     */
    public String getEndpoint() { return endpoint; }

    /**
     * Gets the HTTP method used.
     *
     * @return the HTTP method
     */
    public String getMethod() { return method; }

    /**
     * Gets the HTTP response status code.
     *
     * @return the response status code
     */
    public Integer getResponseStatus() { return responseStatus; }

    /**
     * Gets the user agent string.
     *
     * @return the user agent
     */
    public String getUserAgent() { return userAgent; }

    /**
     * Gets the request body.
     *
     * @return the request body as a map
     */
    public Map<String, Object> getRequestBody() { return requestBody; }

    /**
     * Gets the response body.
     *
     * @return the response body as a map
     */
    public Map<String, Object> getResponseBody() { return responseBody; }
}
