package com.resend.core.helper;

import com.resend.core.net.ListParams;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * URL Helper for handling query params.
 */
public class URLHelper {

    /**
     * Parses the given {@link ListParams} object into a query string.
     *
     * @param params The {@link ListParams} instance (can be null).
     * @return A query string starting with "?" if parameters exist, or an empty string otherwise.
     */
    public static String parse(ListParams params) {
        if (params == null) {
            return "";
        }

        Map<String, String> queryParams = new LinkedHashMap<>();

        if (params.getLimit() != null) {
            queryParams.put("limit", params.getLimit().toString());
        }
        if (params.getAfter() != null && !params.getAfter().isEmpty()) {
            queryParams.put("after", params.getAfter());
        }
        if (params.getBefore() != null && !params.getBefore().isEmpty()) {
            queryParams.put("before", params.getBefore());
        }

        if (queryParams.isEmpty()) {
            return "";
        }

        String query = queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        return "?" + query;
    }


}
