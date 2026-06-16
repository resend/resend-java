package com.resend.services.contacts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.service.BaseService;
import com.resend.services.contacts.model.CreateContactImportOptions;
import com.resend.services.contacts.model.CreateContactImportResponseSuccess;
import com.resend.services.contacts.model.GetContactImportResponseSuccess;
import com.resend.services.contacts.model.ListContactImportsParams;
import com.resend.services.contacts.model.ListContactImportsResponseSuccess;
import okhttp3.MediaType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the Contact Imports sub-service.
 *
 * <p>Handles operations for importing contacts from CSV files. The {@code create} request
 * is sent as {@code multipart/form-data}.</p>
 *
 * <p><strong>Note:</strong> Contact Imports is currently in beta and only available to a
 * limited number of users. APIs might change before GA.</p>
 */
public class ContactImports extends BaseService {

    private static final MediaType CSV_MEDIA_TYPE = MediaType.get("text/csv");

    /**
     * Constructs an instance of the {@code ContactImports} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public ContactImports(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a new contact import from a CSV file.
     *
     * @param options The contact import options. {@code file} is required.
     * @return The details of the created contact import.
     * @throws ResendException If an error occurs during the contact import creation process.
     */
    public CreateContactImportResponseSuccess create(CreateContactImportOptions options) throws ResendException {
        if (options == null || (options.getFile() == null && options.getFileBytes() == null)) {
            throw new IllegalArgumentException("file must be provided");
        }

        Map<String, String> formFields = new LinkedHashMap<>();

        if (options.getColumnMap() != null) {
            formFields.put("column_map", super.resendMapper.writeValue(options.getColumnMap()));
        }
        if (options.getOnConflict() != null) {
            formFields.put("on_conflict", options.getOnConflict());
        }
        if (options.getSegments() != null) {
            formFields.put("segments", super.resendMapper.writeValue(options.getSegments()));
        }
        if (options.getTopics() != null) {
            formFields.put("topics", super.resendMapper.writeValue(options.getTopics()));
        }

        AbstractHttpResponse<String> response;
        if (options.getFile() != null) {
            response = httpClient.performMultipart(
                    "/contacts/imports",
                    super.apiKey,
                    HttpMethod.POST,
                    options.getFile(),
                    CSV_MEDIA_TYPE,
                    formFields);
        } else {
            response = httpClient.performMultipart(
                    "/contacts/imports",
                    super.apiKey,
                    HttpMethod.POST,
                    options.getFileBytes(),
                    options.getFileName(),
                    CSV_MEDIA_TYPE,
                    formFields);
        }

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        return resendMapper.readValue(response.getBody(), CreateContactImportResponseSuccess.class);
    }

    /**
     * Retrieves a single contact import by its ID.
     *
     * @param contactImportId The contact import ID.
     * @return The retrieved contact import details.
     * @throws ResendException If an error occurs while retrieving the contact import.
     */
    public GetContactImportResponseSuccess get(String contactImportId) throws ResendException {
        if (contactImportId == null || contactImportId.isEmpty()) {
            throw new IllegalArgumentException("Contact import ID must be provided");
        }

        AbstractHttpResponse<String> response = this.httpClient.perform(
                "/contacts/imports/" + contactImportId,
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        return resendMapper.readValue(response.getBody(), GetContactImportResponseSuccess.class);
    }

    /**
     * Retrieves a list of contact imports.
     *
     * @return The list of contact imports.
     * @throws ResendException If an error occurs during the contact imports list retrieval process.
     */
    public ListContactImportsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform(
                "/contacts/imports",
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        return resendMapper.readValue(response.getBody(), ListContactImportsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of contact imports, optionally filtered by status.
     *
     * @param params The query parameters used to customize the list.
     * @return The list of contact imports matching the supplied parameters.
     * @throws ResendException If an error occurs during the contact imports list retrieval process.
     */
    public ListContactImportsResponseSuccess list(ListContactImportsParams params) throws ResendException {
        String pathWithQuery = "/contacts/imports" + buildQueryString(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(
                pathWithQuery,
                super.apiKey,
                HttpMethod.GET,
                null,
                MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        return resendMapper.readValue(response.getBody(), ListContactImportsResponseSuccess.class);
    }

    private static String buildQueryString(ListContactImportsParams params) {
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
        if (params.getStatus() != null && !params.getStatus().isEmpty()) {
            queryParams.put("status", params.getStatus());
        }

        if (queryParams.isEmpty()) {
            return "";
        }

        return "?" + queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&"));
    }

    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
