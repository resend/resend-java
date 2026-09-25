package com.resend.services.usage;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
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

    Usage(final String apiKey, final IHttpClient httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Retrieves the caller's account-level usage and quota data.
     *
     * @return The account's usage details.
     * @throws ResendException If an error occurs while retrieving the usage data.
     */
    public UsageResponse get() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/usage", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        return resendMapper.readValue(response.getBody(), UsageResponse.class);
    }
}
