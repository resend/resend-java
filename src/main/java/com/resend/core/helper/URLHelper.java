package com.resend.core.helper;

import com.resend.core.net.ListParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * URL Helper for handling path segments and query params.
 */
public class URLHelper {

    /**
     * Private constructor to prevent instantiation.
     */
    private URLHelper() {
    }

    /**
     * Parses the given {@link ListParams} object into a query string.
     *
     * @param params The {@link ListParams} instance (can be null).
     * @return A query string starting with "?" if parameters exist, or an empty string otherwise.
     */
    public static String parse(ListParams params) {
        return parse(params, Collections.emptyMap());
    }

    /**
     * Parses the given {@link ListParams} plus additional query parameters into a query string.
     *
     * <p>Values from {@code extras} are appended after the base pagination params, in the order
     * supplied. Entries with {@code null} or empty values are skipped.</p>
     *
     * @param params The {@link ListParams} instance (can be null).
     * @param extras Additional query parameters to include (can be empty/null).
     * @return A query string starting with "?" if parameters exist, or an empty string otherwise.
     */
    public static String parse(ListParams params, Map<String, String> extras) {
        Map<String, String> queryParams = new LinkedHashMap<>();

        if (params != null) {
            if (params.getLimit() != null) {
                queryParams.put("limit", params.getLimit().toString());
            }
            if (params.getAfter() != null && !params.getAfter().isEmpty()) {
                queryParams.put("after", params.getAfter());
            }
            if (params.getBefore() != null && !params.getBefore().isEmpty()) {
                queryParams.put("before", params.getBefore());
            }
        }

        if (extras != null) {
            for (Map.Entry<String, String> entry : extras.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                    queryParams.put(entry.getKey(), entry.getValue());
                }
            }
        }

        if (queryParams.isEmpty()) {
            return "";
        }

        String query = queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&"));

        return "?" + query;
    }

    /**
     * Encodes a value for use as a single path segment, such as an ID or an email address.
     * Characters that would change the path, like {@code /}, {@code ?}, {@code #} and {@code %}, are percent-encoded.
     *
     * @param value The path segment value.
     * @return The encoded path segment.
     * @throws IllegalArgumentException If the value is null, empty, {@code .} or {@code ..}, which cannot be sent as a
     *                                  single segment.
     */
    public static String encodePathSegment(String value) {
        if (value == null || value.isEmpty() || value.equals(".") || value.equals("..")) {
            throw new IllegalArgumentException("A path segment must not be null, empty, \".\" or \"..\"");
        }
        return encode(value).replace("+", "%20");
    }

    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
