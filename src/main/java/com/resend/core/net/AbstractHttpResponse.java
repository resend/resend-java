package com.resend.core.net;

/**
 * Represents an HTTP response containing the response code, body, and success status.
 *
 * @param <T> The type of the response body.
 */
public class AbstractHttpResponse<T> {

    /** The HTTP response code. */
    private int code;

    /** The response body. */
    private T body;

    /** Indicates whether the HTTP request was successful. */
    private boolean isSuccessful;

    /**
     * Constructs an instance of AbstractHttpResponse with the provided values.
     *
     * @param code        The HTTP response code.
     * @param body        The response body.
     * @param isSuccessful Whether the HTTP request was successful.
     */
    public AbstractHttpResponse(int code, T body, boolean isSuccessful) {
        this.code = code;
        this.body = body;
        this.isSuccessful = isSuccessful;
    }

    /**
     * Gets the HTTP response code.
     *
     * @return The HTTP response code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the HTTP response code.
     *
     * @param code The HTTP response code to set.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the response body.
     *
     * @return The response body.
     */
    public T getBody() {
        return body;
    }

    /**
     * Sets the response body.
     *
     * @param body The response body to set.
     */
    public void setBody(T body) {
        this.body = body;
    }

    /**
     * Checks whether the HTTP request was successful.
     *
     * @return {@code true} if the HTTP request was successful, otherwise {@code false}.
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }

    /**
     * Sets the success status of the HTTP request.
     *
     * @param successful The success status to set.
     */
    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}

