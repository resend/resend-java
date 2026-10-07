package com.resend.services.usage;

import com.resend.core.exception.ResendException;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.service.BaseService;
import com.resend.services.usage.model.UsageResponse;
import okhttp3.MediaType;

/**
 * Represents the Resend Usage module.
 */
public class Usage extends BaseService {

    /**
     * Constructs an instance of the {@code Usage} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Usage(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Usage} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Usage(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves the caller's account-level usage and quota data.
     *
     * @return The account's usage details.
     * @throws ResendException If an error occurs while retrieving the usage data.
     */
    public UsageResponse get() throws ResendException {
        return execute("/usage", HttpMethod.GET, null, MediaType.get("application/json"), UsageResponse.class);
    }
}
