package com.resend;

import com.resend.services.apikeys.ApiKeys;
import com.resend.services.domains.Domains;
import com.resend.services.emails.Emails;

/**
 * The Resend class provides a facade for the Domains and Emails services.
 */
public class Resend {

    /**
     * The API key for the Resend service.
     */
    private final String apiKey;

    /**
     * Constructs a new Resend with the specified API key.
     *
     * @param apiKey The API key for the ReSend service.
     */
    public Resend(final String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Returns a Domains object that can be used to interact with the Domains service.
     *
     * @return A Domains object.
     */
    public Domains domains() {
        return new Domains(apiKey);
    }

    /**
     * Returns an Emails object that can be used to interact with the Emails service.
     *
     * @return An Emails object.
     */
    public Emails emails() {
        return new Emails(apiKey);
    }

    /**
     * Returns an ApiKeys object that can be used to interact with the ApiKeys service.
     *
     * @return An ApiKeys object.
     */
    public ApiKeys apiKeys() {
        return new ApiKeys(apiKey);
    }
}
