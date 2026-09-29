package com.resend.core.exception;

import org.json.JSONException;
import org.json.JSONObject;
import org.jspecify.annotations.Nullable;

/**
 * Exception class for representing errors related to the Resend API.
 */
public class ResendException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * The HTTP status code of the response that triggered this exception.
     */
    private @Nullable Integer statusCode;

    /**
     * The error name/type returned.
     */
    private @Nullable String errorName;

    /**
     * The raw response body from the API.
     */
    private @Nullable String responseBody;

    /**
     * Constructs a new `ResendException` with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public ResendException(String message) {
        super(message);
    }

    /**
     * Constructs a new `ResendException` with the specified error message and cause.
     *
     * @param message The error message describing the exception.
     * @param cause   The cause of the exception.
     */
    public ResendException(String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new `ResendException` with HTTP status code and response body.
     * Automatically parses the JSON response to extract error details if possible.
     *
     * @param statusCode   The HTTP status code.
     * @param responseBody The raw response body from the API.
     */
    public ResendException(int statusCode, @Nullable String responseBody) {
        super(parseMessage(responseBody));
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.errorName = parseErrorName(responseBody);
    }

    /**
     * Constructs a new `ResendException` with a custom message, status code, and response body.
     *
     * @param message      The error message describing the exception.
     * @param statusCode   The HTTP status code.
     * @param responseBody The raw response body from the API.
     */
    public ResendException(String message, int statusCode, @Nullable String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.errorName = parseErrorName(responseBody);
    }

    /**
     * Parses the error message from the JSON response body.
     * Falls back to the raw response body if parsing fails.
     *
     * @param responseBody The raw response body.
     * @return The parsed error message or the raw response body.
     */
    private static String parseMessage(@Nullable String responseBody) {
        if (responseBody == null || responseBody.isEmpty()) {
            return "Unknown error";
        }

        try {
            JSONObject json = new JSONObject(responseBody);
            if (json.has("message")) {
                return json.getString("message");
            }
        } catch (JSONException e) {
            // Not valid JSON, return raw body
        }

        return responseBody;
    }

    /**
     * Parses the error name from the JSON response body.
     *
     * @param responseBody The raw response body.
     * @return The error name if found, null otherwise.
     */
    private static @Nullable String parseErrorName(@Nullable String responseBody) {
        if (responseBody == null || responseBody.isEmpty()) {
            return null;
        }

        try {
            JSONObject json = new JSONObject(responseBody);
            if (json.has("name")) {
                return json.getString("name");
            }
        } catch (JSONException e) {
            // Not valid JSON or no name field
        }

        return null;
    }

    /**
     * Returns the HTTP status code of the response that triggered this exception.
     *
     * @return the HTTP status code, or null if not set.
     */
    public @Nullable Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Returns the error name/type.
     *
     * @return the error name (e.g., "validation_error"), or null if not set.
     */
    public @Nullable String getErrorName() {
        return errorName;
    }

    /**
     * Returns the raw response body from the API.
     *
     * @return the response body, or null if not set.
     */
    public @Nullable String getResponseBody() {
        return responseBody;
    }

    @Override
    public String toString() {
        return "ResendException{" +
                "statusCode=" + statusCode +
                ", errorName='" + errorName + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}