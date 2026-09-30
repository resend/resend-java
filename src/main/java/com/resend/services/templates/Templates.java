package com.resend.services.templates;

import com.resend.ResendOptions;
import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
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
     * Constructs an instance of the {@code Templates} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Templates(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates a new template.
     *
     * @param options The options for creating the template.
     * @return The response indicating the status of the template creation.
     * @throws ResendException If an error occurs while creating the template.
     */
    public CreateTemplateResponseSuccess create(CreateTemplateOptions options) throws ResendException {
        String payload = super.resendMapper.writeValue(options);
        return execute("/templates", HttpMethod.POST, payload, MediaType.get("application/json"), CreateTemplateResponseSuccess.class);
    }

    /**
     * Retrieves a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The retrieved template's details.
     * @throws ResendException If an error occurs while retrieving the template.
     */
    public GetTemplateResponseSuccess get(String templateId) throws ResendException {
        return execute("/templates/" + templateId, HttpMethod.GET, null, MediaType.get("application/json"), GetTemplateResponseSuccess.class);
    }

    /**
     * Retrieves a list of templates.
     *
     * @return A ListTemplatesResponse containing the list of templates.
     * @throws ResendException If an error occurs during the templates list retrieval process.
     */
    public ListTemplatesResponseSuccess list() throws ResendException {
        return execute("/templates", HttpMethod.GET, null, MediaType.get("application/json"), ListTemplatesResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of templates.
     *
     * @param params The params used to customize the list.
     * @return A ListTemplatesResponse containing the paginated list of templates.
     * @throws ResendException If an error occurs during the templates list retrieval process.
     */
    public ListTemplatesResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/templates" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), ListTemplatesResponseSuccess.class);
    }

    /**
     * Updates a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @param options    The options for updating the template.
     * @return The response indicating the status of the template update.
     * @throws ResendException If an error occurs while updating the template.
     */
    public UpdateTemplateResponseSuccess update(String templateId, UpdateTemplateOptions options) throws ResendException {
        String payload = super.resendMapper.writeValue(options);
        return execute("/templates/" + templateId, HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateTemplateResponseSuccess.class);
    }

    /**
     * Deletes a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The response indicating the status of the template deletion.
     * @throws ResendException If an error occurs while deleting the template.
     */
    public DeleteTemplateResponseSuccess remove(String templateId) throws ResendException {
        return execute("/templates/" + templateId, HttpMethod.DELETE, null, MediaType.get("application/json"), DeleteTemplateResponseSuccess.class);
    }

    /**
     * Duplicates a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The response indicating the status of the template duplication.
     * @throws ResendException If an error occurs while duplicating the template.
     */
    public DuplicateTemplateResponseSuccess duplicate(String templateId) throws ResendException {
        return execute("/templates/" + templateId + "/duplicate", HttpMethod.POST, "", MediaType.get("application/json"), DuplicateTemplateResponseSuccess.class);
    }

    /**
     * Publishes a template by its unique identifier or alias.
     *
     * @param templateId The unique identifier or alias of the template.
     * @return The response indicating the status of the template publication.
     * @throws ResendException If an error occurs while publishing the template.
     */
    public PublishTemplateResponseSuccess publish(String templateId) throws ResendException {
        return execute("/templates/" + templateId + "/publish", HttpMethod.POST, "", MediaType.get("application/json"), PublishTemplateResponseSuccess.class);
    }
}
