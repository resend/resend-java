package com.resend.core.exception;

/**
 * Custom exception class for representing errors related to the Resend API.
 */
public class ResendException extends RuntimeException {

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
    public ResendException(String message, Throwable cause) {
        super(message, cause);
    }
}

