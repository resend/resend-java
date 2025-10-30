package com.resend;

import com.resend.services.apikeys.ApiKeys;
import com.resend.services.audiences.Audiences;
import com.resend.services.batch.Batch;
import com.resend.services.broadcasts.Broadcasts;
import com.resend.services.contacts.Contacts;
import com.resend.services.domains.Domains;
import com.resend.services.emails.Emails;
import com.resend.services.segments.Segments;
import com.resend.services.webhooks.Webhooks;
import com.resend.services.receiving.Receiving;
import com.resend.services.topics.Topics;
import com.resend.services.templates.Templates;

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

    /**
     * Returns a Contacts object that can be used to interact with the Contacts service.
     *
     * @return A Contact object.
     */
    public Contacts contacts() {
        return new Contacts(apiKey);
    }

    /**
     * Returns an Audience object that can be used to interact with the Audiences service.
     *
     * @return an Audiences object.
     * @deprecated Use {@link #segments()} instead.
     */
    @Deprecated
    public Audiences audiences() {
        return new Audiences(apiKey);
    }

    /**
     * Returns a Segments object that can be used to interact with the Segments service.
     *
     * @return a Segments object.
     */
    public Segments segments() {
        return new Segments(apiKey);
    }

    /**
     * Returns a Batch object that can be used to interact with the Batch service.
     *
     * @return An Batch object.
     */
    public Batch batch() {
        return new Batch(apiKey);
    }

    /**
     * Returns a Broadcasts object that can be used to interact with the Broadcasts service.
     *
     * @return A Broadcasts object.
     */
    public Broadcasts broadcasts() {
        return new Broadcasts(apiKey);
    }

    /**
     * Returns a Webhooks object that can be used to interact with the Webhooks service.
     *
     * @return A Webhooks object.
     */
    public Webhooks webhooks() {
        return new Webhooks(apiKey);
    }
  
    /** 
     * Returns a Receiving object that can be used to interact with the Receiving service for inbound emails.
     *
     * @return A Receiving object.
     */
    public Receiving receiving() {
        return new Receiving(apiKey);
    }
  
    /**
     * Returns a Topics object that can be used to interact with the Topics service.
     *
     * @return A Topics object.
     */
    public Topics topics() {
        return new Topics(apiKey);
    }
  
    /**
     * Returns a Templates object that can be used to interact with the Templates service.
     *
     * @return A Templates object.
     */
    public Templates templates() {
        return new Templates(apiKey);
    }
}
