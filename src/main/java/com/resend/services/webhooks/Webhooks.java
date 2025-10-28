package com.resend.services.webhooks;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.webhooks.model.CreateWebhookOptions;
import com.resend.services.webhooks.model.CreateWebhookResponseSuccess;
import com.resend.services.webhooks.model.ListWebhooksResponseSuccess;
import com.resend.services.webhooks.model.RemoveWebhookResponseSuccess;
import com.resend.services.webhooks.model.UpdateWebhookOptions;
import com.resend.services.webhooks.model.UpdateWebhookResponseSuccess;
import com.resend.services.webhooks.model.GetWebhookResponseSuccess;
import okhttp3.MediaType;

/**
 * Represents the Resend Webhooks module.
 */
public final class Webhooks extends BaseService {

    /**
     * Constructs an instance of the {@code Webhooks} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Webhooks(final String apiKey) {
        super(apiKey);
    }

    /**
     * Creates a webhook based on the provided CreateWebhookOptions and returns a CreateWebhookResponseSuccess.
     *
     * @param createWebhookOptions The request object containing the webhook creation details.
     * @return A CreateWebhookResponseSuccess representing the result of the webhook creation operation.
     * @throws ResendException If an error occurs during the webhook creation process.
     */
    public CreateWebhookResponseSuccess create(CreateWebhookOptions createWebhookOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createWebhookOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateWebhookResponseSuccess.class);
    }

    /**
     * Updates a webhook based on the provided webhook ID and UpdateWebhookOptions, and returns an UpdateWebhookResponseSuccess.
     *
     * @param webhookId The unique identifier of the webhook to update.
     * @param updateWebhookOptions The object containing the information to be updated.
     * @return An UpdateWebhookResponseSuccess representing the result of the webhook update operation.
     * @throws ResendException If an error occurs during the webhook update process.
     */
    public UpdateWebhookResponseSuccess update(String webhookId, UpdateWebhookOptions updateWebhookOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateWebhookOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks/" + webhookId, super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, UpdateWebhookResponseSuccess.class);
    }

    /**
     * Retrieves a webhook based on the provided webhook ID and returns a Webhook object.
     *
     * @param webhookId The unique identifier of the webhook to retrieve.
     * @return A Webhook object representing the retrieved webhook.
     * @throws ResendException If an error occurs during the webhook retrieval process.
     */
    public GetWebhookResponseSuccess get(String webhookId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks/" + webhookId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, GetWebhookResponseSuccess.class);
    }

    /**
     * Retrieves a list of webhooks and returns a ListWebhooksResponseSuccess.
     *
     * @return A ListWebhooksResponseSuccess containing the list of webhooks.
     * @throws ResendException If an error occurs during the webhook list retrieval process.
     */
    public ListWebhooksResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/webhooks", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListWebhooksResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of webhooks and returns a ListWebhooksResponseSuccess.
     *
     * @param params The params used to customize the list.
     * @return A ListWebhooksResponseSuccess containing the paginated list of webhooks.
     * @throws ResendException If an error occurs during the webhook list retrieval process.
     */
    public ListWebhooksResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/webhooks" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListWebhooksResponseSuccess.class);
    }

    /**
     * Deletes a webhook based on the provided webhook ID and returns a RemoveWebhookResponseSuccess.
     *
     * @param webhookId The unique identifier of the webhook to delete.
     * @return A RemoveWebhookResponseSuccess representing the result of the webhook deletion operation.
     * @throws ResendException If an error occurs during the webhook deletion process.
     */
    public RemoveWebhookResponseSuccess remove(String webhookId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/webhooks/" + webhookId, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, RemoveWebhookResponseSuccess.class);
    }
}
