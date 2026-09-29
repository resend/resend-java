package com.resend;

import com.resend.services.apikeys.ApiKeys;
import com.resend.services.audiences.Audiences;
import com.resend.services.automations.Automations;
import com.resend.services.batch.Batch;
import com.resend.services.broadcasts.Broadcasts;
import com.resend.services.contacts.Contacts;
import com.resend.services.contactproperties.ContactProperties;
import com.resend.services.domains.Domains;
import com.resend.services.emails.Emails;
import com.resend.services.segments.Segments;
import com.resend.services.suppressions.Suppressions;
import com.resend.services.webhooks.Webhooks;
import com.resend.services.receiving.Receiving;
import com.resend.services.topics.Topics;
import com.resend.services.events.Events;
import com.resend.services.logs.Logs;
import com.resend.services.oauthgrants.OAuthGrants;
import com.resend.services.templates.Templates;
import com.resend.services.usage.Usage;

/**
 * Entry point for the Resend Java SDK.
 *
 * <p>Create one instance per API key and access feature modules through methods like
 * {@link #emails()}, {@link #domains()}, and {@link #contacts()}.</p>
 */
public class Resend {

    /**
     * The API key for the Resend service.
     */
    private final String apiKey;

    /**
     * Optional client configuration (base URL, User-Agent, timeouts).
     */
    private final ResendOptions options;

    /**
     * Constructs a new Resend client with the specified API key and default options.
     *
     * @param apiKey The API key for the Resend service.
     * @throws IllegalArgumentException If {@code apiKey} is {@code null} or blank.
     */
    public Resend(final String apiKey) {
        this(apiKey, ResendOptions.defaults());
    }

    /**
     * Constructs a new Resend client with the specified API key and options.
     *
     * @param apiKey  The API key for the Resend service.
     * @param options Client options such as base URL and timeouts. {@code null} uses defaults.
     * @throws IllegalArgumentException If {@code apiKey} is {@code null} or blank.
     */
    public Resend(final String apiKey, final ResendOptions options) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Missing API key. Pass it to the constructor: new Resend(\"re_123\")");
        }
        this.apiKey = apiKey;
        this.options = options != null ? options : ResendOptions.defaults();
    }

    /**
     * Returns the client options used by this instance.
     *
     * @return The {@link ResendOptions}.
     */
    public ResendOptions getOptions() {
        return options;
    }

    /**
     * Returns a Domains object that can be used to interact with the Domains service.
     *
     * @return A Domains object.
     */
    public Domains domains() {
        return new Domains(apiKey, options);
    }

    /**
     * Returns an Emails object that can be used to interact with the Emails service.
     *
     * @return An Emails object.
     */
    public Emails emails() {
        return new Emails(apiKey, options);
    }

    /**
     * Returns an ApiKeys object that can be used to interact with the ApiKeys service.
     *
     * @return An ApiKeys object.
     */
    public ApiKeys apiKeys() {
        return new ApiKeys(apiKey, options);
    }

    /**
     * Returns a Contacts object that can be used to interact with the Contacts service.
     *
     * @return A Contacts object.
     */
    public Contacts contacts() {
        return new Contacts(apiKey, options);
    }

    /**
     * Returns a ContactProperties object that can be used to interact with the ContactProperties service.
     *
     * @return A ContactProperties object.
     */
    public ContactProperties contactProperties() {
        return new ContactProperties(apiKey, options);
    }

    /**
     * Returns an Audiences object that can be used to interact with the Audiences service.
     *
     * @return An Audiences object.
     * @deprecated Use {@link #segments()} instead.
     */
    @Deprecated
    public Audiences audiences() {
        return new Audiences(apiKey, options);
    }

    /**
     * Returns a Segments object that can be used to interact with the Segments service.
     *
     * @return A Segments object.
     */
    public Segments segments() {
        return new Segments(apiKey, options);
    }

    /**
     * Returns a Batch object that can be used to interact with the Batch service.
     *
     * @return A Batch object.
     */
    public Batch batch() {
        return new Batch(apiKey, options);
    }

    /**
     * Returns a Broadcasts object that can be used to interact with the Broadcasts service.
     *
     * @return A Broadcasts object.
     */
    public Broadcasts broadcasts() {
        return new Broadcasts(apiKey, options);
    }

    /**
     * Returns a Webhooks object that can be used to interact with the Webhooks service.
     *
     * @return A Webhooks object.
     */
    public Webhooks webhooks() {
        return new Webhooks(apiKey, options);
    }
  
    /** 
     * Returns a Receiving object that can be used to interact with the Receiving service for inbound emails.
     *
     * @return A Receiving object.
     */
    public Receiving receiving() {
        return new Receiving(apiKey, options);
    }
  
    /**
     * Returns a Topics object that can be used to interact with the Topics service.
     *
     * @return A Topics object.
     */
    public Topics topics() {
        return new Topics(apiKey, options);
    }
  
    /**
     * Returns a Templates object that can be used to interact with the Templates service.
     *
     * @return A Templates object.
     */
    public Templates templates() {
        return new Templates(apiKey, options);
    }

    /**
     * Returns a Logs object that can be used to interact with the Logs service.
     *
     * @return A Logs object.
     */
    public Logs logs() {
        return new Logs(apiKey, options);
    }

    /**
     * Returns an Events object that can be used to interact with the Events service.
     *
     * @return An Events object.
     */
    public Events events() {
        return new Events(apiKey, options);
    }

    /**
     * Returns an Automations object that can be used to interact with the Automations service.
     *
     * @return An Automations object.
     */
    public Automations automations() {
        return new Automations(apiKey, options);
    }

    /**
     * Returns a Suppressions object that can be used to interact with the Suppressions service.
     *
     * @return A Suppressions object.
     */
    public Suppressions suppressions() {
        return new Suppressions(apiKey, options);
    }

    /**
     * Returns an OAuthGrants object that can be used to interact with the OAuthGrants service.
     *
     * @return An OAuthGrants object.
     */
    public OAuthGrants oauthGrants() {
        return new OAuthGrants(apiKey, options);
    }

    /**
     * Returns a Usage object that can be used to interact with the Usage service.
     *
     * @return A Usage object.
     */
    public Usage usage() {
        return new Usage(apiKey, options);
    }
}
