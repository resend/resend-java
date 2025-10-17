package com.resend.services.apikeys;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
import com.resend.core.service.BaseService;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.CreateApiKeyOptions;
import com.resend.services.apikeys.model.ListApiKeysResponse;
import okhttp3.MediaType;

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
     * Creates an API key.
     *
     * @param createApiKeyOptions The request the API key details.
     * @return The response indicating the state of the api key.
     * @throws ResendException If an error occurs during the API key creation process.
     */
    public CreateApiKeyResponse create(CreateApiKeyOptions createApiKeyOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createApiKeyOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/api-keys", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        CreateApiKeyResponse createApiKeyResponseResponse = resendMapper.readValue(responseBody, CreateApiKeyResponse.class);

        return createApiKeyResponseResponse;
    }

    /**
     * Retrieves a list of api keys and returns a ListApiKeysResponse.
     *
     * @return A ListApiKeysResponse containing the list of api keys.
     * @throws ResendException If an error occurs during the api keys list retrieval process.
     */
    public ListApiKeysResponse list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/api-keys", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        ListApiKeysResponse listApiKeysResponse = resendMapper.readValue(responseBody, ListApiKeysResponse.class);
        return listApiKeysResponse;
    }

    /**
     * Retrieves a paginated list of api keys and returns a ListApiKeysResponse.
     * @param params The params used to customize the list.
     *
     * @return A ListApiKeysResponse containing the paginated list of api keys.
     * @throws ResendException If an error occurs during the api keys list retrieval process.
     */
    public ListApiKeysResponse list(ListParams params) throws ResendException {
        String pathWithQuery = "/api-keys" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();

        ListApiKeysResponse listApiKeysResponse = resendMapper.readValue(responseBody, ListApiKeysResponse.class);
        return listApiKeysResponse;
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
