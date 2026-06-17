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
 * URL Helper for handling query params.
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

    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
