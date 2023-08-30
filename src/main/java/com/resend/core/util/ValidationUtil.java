package com.resend.core.util;

/**
 * Utility class for common validation messages and error strings.
 */
public class ValidationUtil {
    /**
     * Error message for a null "From" email address.
     */
    public static final String EMAIL_FROM_NULL = "From address is mandatory and cannot be null";

    /**
     * Error message for a null "To" email address.
     */
    public static final String EMAIL_TO_NULL = "To address is mandatory and cannot be null";

    /**
     * Error message for a null email subject.
     */
    public static final String EMAIL_SUBJECT_NULL = "Subject is mandatory and cannot be null";

    /**
     * Error message for a null tag name.
     */
    public static final String TAG_NAME_NULL = "Tag name is mandatory and cannot be null";

    /**
     * Error message for a null domain name.
     */
    public static final String DOMAIN_NAME_NULL = "Domain name is mandatory and cannot be null";
}

