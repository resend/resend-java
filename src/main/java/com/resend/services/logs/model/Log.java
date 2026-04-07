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

    public String getId() { return id; }
    public String getCreatedAt() { return createdAt; }
    public String getEndpoint() { return endpoint; }
    public String getMethod() { return method; }
    public Integer getResponseStatus() { return responseStatus; }
    public String getUserAgent() { return userAgent; }
    public Map<String, Object> getRequestBody() { return requestBody; }
    public Map<String, Object> getResponseBody() { return responseBody; }
}
