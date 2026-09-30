package com.resend;

import com.resend.core.net.IHttpClient;
import com.resend.core.net.impl.HttpClient;
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
import okhttp3.OkHttpClient;

import java.net.Proxy;
import java.time.Duration;

/**
 * The Resend class is the entry point to every Resend API service.
 *
 * <p>Create one with {@link #Resend(String)} for the defaults, or with {@link #builder()} to set a custom base URL,
 * timeouts, a proxy or your own {@link IHttpClient}. Every service returned by an instance shares the same HTTP
 * client, so create one {@code Resend} and reuse it.</p>
 */
public class Resend {

    /**
     * The API key for the Resend service.
     */
    private final String apiKey;

    /**
     * The HTTP client shared by every service created from this instance.
     */
    private final IHttpClient<String> httpClient;

    /**
     * Constructs a new Resend with the specified API key and the default HTTP client.
     *
     * @param apiKey The API key for the Resend service.
     * @throws IllegalArgumentException If {@code apiKey} is {@code null} or blank.
     */
    public Resend(final String apiKey) {
        this(apiKey, HttpClient.getDefault());
    }

    private Resend(final String apiKey, final IHttpClient<String> httpClient) {
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    /**
     * Returns a builder for a Resend instance with a custom configuration.
     *
     * <pre>{@code
     * Resend resend = Resend.builder()
     *         .apiKey("re_123")
     *         .readTimeout(Duration.ofSeconds(30))
     *         .build();
     * }</pre>
     *
     * @return A new builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a Domains object that can be used to interact with the Domains service.
     *
     * @return A Domains object.
     */
    public Domains domains() {
        return new Domains(apiKey, httpClient);
    }

    /**
     * Returns an Emails object that can be used to interact with the Emails service.
     *
     * @return An Emails object.
     */
    public Emails emails() {
        return new Emails(apiKey, httpClient);
    }

    /**
     * Returns an ApiKeys object that can be used to interact with the ApiKeys service.
     *
     * @return An ApiKeys object.
     */
    public ApiKeys apiKeys() {
        return new ApiKeys(apiKey, httpClient);
    }

    /**
     * Returns a Contacts object that can be used to interact with the Contacts service.
     *
     * @return A Contacts object.
     */
    public Contacts contacts() {
        return new Contacts(apiKey, httpClient);
    }

    /**
     * Returns a ContactProperties object that can be used to interact with the ContactProperties service.
     *
     * @return A ContactProperties object.
     */
    public ContactProperties contactProperties() {
        return new ContactProperties(apiKey, httpClient);
    }

    /**
     * Returns an Audiences object that can be used to interact with the Audiences service.
     *
     * @return An Audiences object.
     * @deprecated Use {@link #segments()} instead.
     */
    @Deprecated
    public Audiences audiences() {
        return new Audiences(apiKey, httpClient);
    }

    /**
     * Returns a Segments object that can be used to interact with the Segments service.
     *
     * @return A Segments object.
     */
    public Segments segments() {
        return new Segments(apiKey, httpClient);
    }

    /**
     * Returns a Batch object that can be used to interact with the Batch service.
     *
     * @return A Batch object.
     */
    public Batch batch() {
        return new Batch(apiKey, httpClient);
    }

    /**
     * Returns a Broadcasts object that can be used to interact with the Broadcasts service.
     *
     * @return A Broadcasts object.
     */
    public Broadcasts broadcasts() {
        return new Broadcasts(apiKey, httpClient);
    }

    /**
     * Returns a Webhooks object that can be used to interact with the Webhooks service.
     *
     * @return A Webhooks object.
     */
    public Webhooks webhooks() {
        return new Webhooks(apiKey, httpClient);
    }
  
    /** 
     * Returns a Receiving object that can be used to interact with the Receiving service for inbound emails.
     *
     * @return A Receiving object.
     */
    public Receiving receiving() {
        return new Receiving(apiKey, httpClient);
    }
  
    /**
     * Returns a Topics object that can be used to interact with the Topics service.
     *
     * @return A Topics object.
     */
    public Topics topics() {
        return new Topics(apiKey, httpClient);
    }
  
    /**
     * Returns a Templates object that can be used to interact with the Templates service.
     *
     * @return A Templates object.
     */
    public Templates templates() {
        return new Templates(apiKey, httpClient);
    }

    /**
     * Returns a Logs object that can be used to interact with the Logs service.
     *
     * @return A Logs object.
     */
    public Logs logs() {
        return new Logs(apiKey, httpClient);
    }

    /**
     * Returns an Events object that can be used to interact with the Events service.
     *
     * @return An Events object.
     */
    public Events events() {
        return new Events(apiKey, httpClient);
    }

    /**
     * Returns an Automations object that can be used to interact with the Automations service.
     *
     * @return An Automations object.
     */
    public Automations automations() {
        return new Automations(apiKey, httpClient);
    }

    /**
     * Returns a Suppressions object that can be used to interact with the Suppressions service.
     *
     * @return A Suppressions object.
     */
    public Suppressions suppressions() {
        return new Suppressions(apiKey, httpClient);
    }

    /**
     * Returns an OAuthGrants object that can be used to interact with the OAuthGrants service.
     *
     * @return An OAuthGrants object.
     */
    public OAuthGrants oauthGrants() {
        return new OAuthGrants(apiKey, httpClient);
    }

    /**
     * Returns a Usage object that can be used to interact with the Usage service.
     *
     * @return A Usage object.
     */
    public Usage usage() {
        return new Usage(apiKey, httpClient);
    }

    /**
     * Builds a {@link Resend} instance with a custom configuration.
     *
     * <p>Only the API key is required. The other options fall into two groups, which can't be combined:</p>
     * <ul>
     *   <li>{@link #baseUrl}, the timeouts and {@link #proxy} configure the built-in HTTP client. When none of them
     *   is set, the instance uses the same shared client as {@link Resend#Resend(String)}; otherwise it gets a
     *   client derived from the shared one, which still reuses its connection pool.</li>
     *   <li>{@link #httpClient} replaces the built-in client entirely. Configure the base URL, timeouts and proxy on
     *   that client instead.</li>
     * </ul>
     */
    public static final class Builder {

        private String apiKey;
        private String baseUrl;
        private Duration connectTimeout;
        private Duration readTimeout;
        private Duration writeTimeout;
        private Duration callTimeout;
        private Proxy proxy;
        private IHttpClient<String> httpClient;

        private Builder() {
        }

        /**
         * Sets the API key used to authenticate requests. Required.
         *
         * @param apiKey The API key.
         * @return This builder.
         */
        public Builder apiKey(final String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Sets the base URL requests are sent to. Defaults to {@value HttpClient#BASE_API}.
         *
         * @param baseUrl The base URL, e.g. {@code https://api.resend.com}. A trailing slash is ignored; a query or
         *                fragment is rejected by {@link #build()}.
         * @return This builder.
         */
        public Builder baseUrl(final String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets the timeout for establishing a connection. Defaults to 10 seconds.
         *
         * @param connectTimeout The connect timeout; {@link Duration#ZERO} means no timeout.
         * @return This builder.
         * @throws IllegalArgumentException If the timeout is negative.
         */
        public Builder connectTimeout(final Duration connectTimeout) {
            this.connectTimeout = requireNonNegative("connectTimeout", connectTimeout);
            return this;
        }

        /**
         * Sets the maximum time to wait for data once a connection is established. Defaults to 10 seconds.
         *
         * @param readTimeout The read timeout; {@link Duration#ZERO} means no timeout.
         * @return This builder.
         * @throws IllegalArgumentException If the timeout is negative.
         */
        public Builder readTimeout(final Duration readTimeout) {
            this.readTimeout = requireNonNegative("readTimeout", readTimeout);
            return this;
        }

        /**
         * Sets the maximum time to wait while sending a request body, e.g. a file upload. Defaults to 10 seconds.
         *
         * @param writeTimeout The write timeout; {@link Duration#ZERO} means no timeout.
         * @return This builder.
         * @throws IllegalArgumentException If the timeout is negative.
         */
        public Builder writeTimeout(final Duration writeTimeout) {
            this.writeTimeout = requireNonNegative("writeTimeout", writeTimeout);
            return this;
        }

        /**
         * Sets the timeout for a complete call, from connecting to reading the whole response. Defaults to no
         * timeout.
         *
         * @param callTimeout The call timeout; {@link Duration#ZERO} means no timeout.
         * @return This builder.
         * @throws IllegalArgumentException If the timeout is negative.
         */
        public Builder callTimeout(final Duration callTimeout) {
            this.callTimeout = requireNonNegative("callTimeout", callTimeout);
            return this;
        }

        /**
         * Sets the proxy requests are routed through, e.g.
         * {@code new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.internal", 3128))}. Defaults to the
         * system's proxy settings.
         *
         * @param proxy The proxy, or {@link Proxy#NO_PROXY} to connect directly.
         * @return This builder.
         */
        public Builder proxy(final Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        /**
         * Sets the HTTP client that executes every request, replacing the built-in one. Use it to plug in another
         * HTTP library, a test double, or {@code new HttpClient(okHttpClient, baseUrl)} to supply your own
         * {@code OkHttpClient} (which requires declaring the {@code com.squareup.okhttp3:okhttp-jvm} dependency).
         *
         * <p>Can't be combined with {@link #baseUrl}, the timeouts or {@link #proxy}; set those on the client.</p>
         *
         * @param httpClient The HTTP client.
         * @return This builder.
         */
        public Builder httpClient(final IHttpClient<String> httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        /**
         * Builds the Resend instance.
         *
         * @return A new Resend instance.
         * @throws IllegalStateException    If no API key was set, or if {@link #httpClient} was combined with
         *                                  options that configure the built-in client.
         * @throws IllegalArgumentException If the base URL is invalid.
         */
        public Resend build() {
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalStateException("apiKey is required");
            }
            if (httpClient != null) {
                if (configuresBuiltInClient()) {
                    throw new IllegalStateException("baseUrl, timeouts and proxy configure the built-in HTTP client "
                            + "and can't be combined with httpClient(...); set them on your client instead");
                }
                return new Resend(apiKey, httpClient);
            }
            return new Resend(apiKey, buildHttpClient());
        }

        private boolean configuresBuiltInClient() {
            return baseUrl != null || hasOkHttpOverrides();
        }

        private boolean hasOkHttpOverrides() {
            return connectTimeout != null || readTimeout != null || writeTimeout != null
                    || callTimeout != null || proxy != null;
        }

        private HttpClient buildHttpClient() {
            if (!configuresBuiltInClient()) {
                return HttpClient.getDefault();
            }

            OkHttpClient client = HttpClient.getDefault().getOkHttpClient();
            if (hasOkHttpOverrides()) {
                // newBuilder() keeps the shared connection pool and dispatcher.
                OkHttpClient.Builder clientBuilder = client.newBuilder();
                if (connectTimeout != null) {
                    clientBuilder.connectTimeout(connectTimeout);
                }
                if (readTimeout != null) {
                    clientBuilder.readTimeout(readTimeout);
                }
                if (writeTimeout != null) {
                    clientBuilder.writeTimeout(writeTimeout);
                }
                if (callTimeout != null) {
                    clientBuilder.callTimeout(callTimeout);
                }
                if (proxy != null) {
                    clientBuilder.proxy(proxy);
                }
                client = clientBuilder.build();
            }
            return new HttpClient(client, baseUrl != null ? baseUrl : HttpClient.BASE_API);
        }

        private static Duration requireNonNegative(final String name, final Duration timeout) {
            if (timeout != null && timeout.isNegative()) {
                throw new IllegalArgumentException(name + " must not be negative, got: " + timeout);
            }
            return timeout;
        }
    }
}
