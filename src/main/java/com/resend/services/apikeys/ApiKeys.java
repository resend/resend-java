package com.resend.services.apikeys;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.CreateApiKeyOptions;
import com.resend.services.apikeys.model.ListApiKeysResponse;
import com.resend.services.apikeys.model.UpdateApiKeyOptions;
import com.resend.services.apikeys.model.UpdateApiKeyResponseSuccess;
import okhttp3.MediaType;
import org.jspecify.annotations.Nullable;

/**
 *  Represents the Resend ApiKeys module.
 */
public final class ApiKeys extends BaseService {

    /**
     * Constructs an instance of the {@code ApiKeys} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public ApiKeys(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code ApiKeys} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public ApiKeys(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates an API key.
     *
     * @param createApiKeyOptions The request the API key details.
     * @return The response indicating the state of the api key.
     * @throws ResendException If an error occurs during the API key creation process.
     */
    public CreateApiKeyResponse create(CreateApiKeyOptions createApiKeyOptions) throws ResendException {
        String payload = serialize(createApiKeyOptions);
        return execute("/api-keys", HttpMethod.POST, payload, MediaType.get("application/json"), CreateApiKeyResponse.class);
    }

    /**
     * Retrieves a list of api keys and returns a ListApiKeysResponse.
     *
     * @return A ListApiKeysResponse containing the list of api keys.
     * @throws ResendException If an error occurs during the api keys list retrieval process.
     */
    public ListApiKeysResponse list() throws ResendException {
        return execute("/api-keys", HttpMethod.GET, null, MediaType.get("application/json"), ListApiKeysResponse.class);
    }

    /**
     * Retrieves a paginated list of api keys and returns a ListApiKeysResponse.
     * @param params The params used to customize the list.
     *
     * @return A ListApiKeysResponse containing the paginated list of api keys.
     * @throws ResendException If an error occurs during the api keys list retrieval process.
     */
    public ListApiKeysResponse list(@Nullable ListParams params) throws ResendException {
        String pathWithQuery = "/api-keys" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), ListApiKeysResponse.class);
    }

    /**
     * Updates an api key by its unique identifier.
     *
     * @param apiKeyId The unique identifier of the api key to update.
     * @param updateApiKeyOptions The new data for the api key.
     * @return The response indicating the status of the api key update.
     * @throws ResendException If an error occurs during the api key update process.
     */
    public UpdateApiKeyResponseSuccess update(String apiKeyId, UpdateApiKeyOptions updateApiKeyOptions) throws ResendException {
        String payload = serialize(updateApiKeyOptions);
        return execute("/api-keys/" + apiKeyId, HttpMethod.PATCH, payload, MediaType.get("application/json"), UpdateApiKeyResponseSuccess.class);
    }

    /**
     * Deletes an api key based on the provided api key ID and returns a boolean response.
     *
     * @param apiKeyId The unique identifier of the api key to delete.
     * @return A boolean representing the result of the api key deletion operation.
     * @throws ResendException If an error occurs during the api key deletion process.
     */
    public boolean remove(String apiKeyId) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/api-keys/" + apiKeyId, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        return true;
    }
}
