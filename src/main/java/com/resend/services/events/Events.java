package com.resend.services.events;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.events.model.*;
import okhttp3.MediaType;

/**
 * Represents the Resend Events module.
 */
public class Events extends BaseService {

    /**
     * Constructs an instance of the {@code Events} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Events(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Events} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Events(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates a new event.
     *
     * @param createEventOptions The options for creating an event.
     * @return The response containing the created event details.
     * @throws ResendException If an error occurs while creating the event.
     */
    public CreateEventResponseSuccess create(CreateEventOptions createEventOptions) throws ResendException {
        return create(createEventOptions, (RequestOptions) null);
    }

    /**
     * Creates a new event.
     *
     * @param createEventOptions The options for creating an event.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the created event details.
     * @throws ResendException If an error occurs while creating the event.
     */
    public CreateEventResponseSuccess create(CreateEventOptions createEventOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createEventOptions);
        return execute("/events", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateEventResponseSuccess.class);
    }

    /**
     * Retrieves an event by its unique identifier or name.
     *
     * @param identifier The unique identifier (UUID) or name of the event.
     * @return The event details.
     * @throws ResendException If an error occurs while retrieving the event.
     */
    public Event get(String identifier) throws ResendException {
        return get(identifier, (RequestOptions) null);
    }

    /**
     * Retrieves an event by its unique identifier or name.
     *
     * @param identifier The unique identifier (UUID) or name of the event.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The event details.
     * @throws ResendException If an error occurs while retrieving the event.
     */
    public Event get(String identifier, RequestOptions requestOptions) throws ResendException {
        return execute("/events/" + identifier, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, Event.class);
    }

    /**
     * Lists all events.
     *
     * @return The response containing the list of events.
     * @throws ResendException If an error occurs while listing the events.
     */
    public ListEventsResponseSuccess list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Lists all events.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the list of events.
     * @throws ResendException If an error occurs while listing the events.
     */
    public ListEventsResponseSuccess list(RequestOptions requestOptions) throws ResendException {
        return execute("/events", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListEventsResponseSuccess.class);
    }

    /**
     * Lists all events with pagination support.
     *
     * @param params The pagination parameters.
     * @return The response containing the list of events.
     * @throws ResendException If an error occurs while listing the events.
     */
    public ListEventsResponseSuccess list(ListParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Lists all events with pagination support.
     *
     * @param params The pagination parameters.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the list of events.
     * @throws ResendException If an error occurs while listing the events.
     */
    public ListEventsResponseSuccess list(ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/events" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListEventsResponseSuccess.class);
    }

    /**
     * Updates an existing event's schema.
     *
     * @param updateEventOptions The options for updating the event.
     * @return The response containing the updated event details.
     * @throws ResendException If an error occurs while updating the event.
     */
    public UpdateEventResponseSuccess update(UpdateEventOptions updateEventOptions) throws ResendException {
        return update(updateEventOptions, (RequestOptions) null);
    }

    /**
     * Updates an existing event's schema.
     *
     * @param updateEventOptions The options for updating the event.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the updated event details.
     * @throws ResendException If an error occurs while updating the event.
     */
    public UpdateEventResponseSuccess update(UpdateEventOptions updateEventOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateEventOptions);
        return execute("/events/" + updateEventOptions.getIdentifier(), HttpMethod.PATCH, payload, MediaType.get("application/json"), requestOptions, UpdateEventResponseSuccess.class);
    }

    /**
     * Removes an event by its unique identifier or name.
     *
     * @param identifier The unique identifier (UUID) or name of the event.
     * @return The response indicating the event was deleted.
     * @throws ResendException If an error occurs while removing the event.
     */
    public RemoveEventResponseSuccess remove(String identifier) throws ResendException {
        return remove(identifier, (RequestOptions) null);
    }

    /**
     * Removes an event by its unique identifier or name.
     *
     * @param identifier The unique identifier (UUID) or name of the event.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response indicating the event was deleted.
     * @throws ResendException If an error occurs while removing the event.
     */
    public RemoveEventResponseSuccess remove(String identifier, RequestOptions requestOptions) throws ResendException {
        return execute("/events/" + identifier, HttpMethod.DELETE, "", null, requestOptions, RemoveEventResponseSuccess.class);
    }

    /**
     * Sends an event to a contact.
     *
     * @param sendEventOptions The options for sending the event.
     * @return The response containing the sent event details.
     * @throws ResendException If an error occurs while sending the event.
     */
    public SendEventResponseSuccess send(SendEventOptions sendEventOptions) throws ResendException {
        return send(sendEventOptions, (RequestOptions) null);
    }

    /**
     * Sends an event to a contact.
     *
     * @param sendEventOptions The options for sending the event.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response containing the sent event details.
     * @throws ResendException If an error occurs while sending the event.
     */
    public SendEventResponseSuccess send(SendEventOptions sendEventOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(sendEventOptions);
        return execute("/events/send", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, SendEventResponseSuccess.class);
    }
}
