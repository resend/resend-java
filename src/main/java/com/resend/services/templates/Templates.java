package com.resend.services.templates;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.templates.model.*;
import okhttp3.MediaType;

/**
 * Represents the Resend Templates module.
 */
public final class Templates extends BaseService {

    /**
     * Constructs an instance of the {@code Templates} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Templates(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a new template.
     *
     * @param options The options for creating the template.
     * @return The response indicating the status of the template creation.
     * @throws ResendException If an error occurs while creating the template.
     */
    public CreateTemplateResponse create(CreateTemplateOptions options) throws ResendException {
        String payload = super.resendMapper.writeValue(options);
        AbstractHttpResponse<String> response = super.httpClient.perform("/templates", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, CreateTemplateResponse.class);
    }

    /**
     * Retrieves a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The retrieved template's details.
     * @throws ResendException If an error occurs while retrieving the template.
     */
    public Template get(String templateId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/templates/" + templateId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, Template.class);
    }

    /**
     * Retrieves a list of templates.
     *
     * @return A ListTemplatesResponse containing the list of templates.
     * @throws ResendException If an error occurs during the templates list retrieval process.
     */
    public ListTemplatesResponse list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/templates", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListTemplatesResponse.class);
    }

    /**
     * Retrieves a paginated list of templates.
     *
     * @param params The params used to customize the list.
     * @return A ListTemplatesResponse containing the paginated list of templates.
     * @throws ResendException If an error occurs during the templates list retrieval process.
     */
    public ListTemplatesResponse list(ListParams params) throws ResendException {
        String pathWithQuery = "/templates" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListTemplatesResponse.class);
    }

    /**
     * Updates a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @param options    The options for updating the template.
     * @return The response indicating the status of the template update.
     * @throws ResendException If an error occurs while updating the template.
     */
    public UpdateTemplateResponse update(String templateId, UpdateTemplateOptions options) throws ResendException {
        String payload = super.resendMapper.writeValue(options);
        AbstractHttpResponse<String> response = this.httpClient.perform("/templates/" + templateId, super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, UpdateTemplateResponse.class);
    }

    /**
     * Deletes a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The response indicating the status of the template deletion.
     * @throws ResendException If an error occurs while deleting the template.
     */
    public DeleteTemplateResponse remove(String templateId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/templates/" + templateId, super.apiKey, HttpMethod.DELETE, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, DeleteTemplateResponse.class);
    }

    /**
     * Duplicates a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The response indicating the status of the template duplication.
     * @throws ResendException If an error occurs while duplicating the template.
     */
    public DuplicateTemplateResponse duplicate(String templateId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/templates/" + templateId + "/duplicate", super.apiKey, HttpMethod.POST, "", MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, DuplicateTemplateResponse.class);
    }

    /**
     * Publishes a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The response indicating the status of the template publication.
     * @throws ResendException If an error occurs while publishing the template.
     */
    public PublishTemplateResponse publish(String templateId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/templates/" + templateId + "/publish", super.apiKey, HttpMethod.POST, "", MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, PublishTemplateResponse.class);
    }
}
