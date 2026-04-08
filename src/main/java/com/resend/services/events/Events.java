package com.resend.services.events;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
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
     * Creates a new event.
     *
     * @param createEventOptions The options for creating an event.
     * @return The response containing the created event details.
     * @throws ResendException If an error occurs while creating the event.
     */
    public CreateEventResponseSuccess create(CreateEventOptions createEventOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createEventOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/events", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateEventResponseSuccess.class);
    }

    /**
     * Retrieves an event by its unique identifier or name.
     *
     * @param identifier The unique identifier (UUID) or name of the event.
     * @return The event details.
     * @throws ResendException If an error occurs while retrieving the event.
     */
    public Event get(String identifier) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/events/" + identifier, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, Event.class);
    }

    /**
     * Lists all events.
     *
     * @return The response containing the list of events.
     * @throws ResendException If an error occurs while listing the events.
     */
    public ListEventsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/events", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListEventsResponseSuccess.class);
    }

    /**
     * Lists all events with pagination support.
     *
     * @param params The pagination parameters.
     * @return The response containing the list of events.
     * @throws ResendException If an error occurs while listing the events.
     */
    public ListEventsResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/events" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListEventsResponseSuccess.class);
    }

    /**
     * Updates an existing event's schema.
     *
     * @param updateEventOptions The options for updating the event.
     * @return The response containing the updated event details.
     * @throws ResendException If an error occurs while updating the event.
     */
    public UpdateEventResponseSuccess update(UpdateEventOptions updateEventOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateEventOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/events/" + updateEventOptions.getIdentifier(), super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, UpdateEventResponseSuccess.class);
    }

    /**
     * Removes an event by its unique identifier or name.
     *
     * @param identifier The unique identifier (UUID) or name of the event.
     * @return The response indicating the event was deleted.
     * @throws ResendException If an error occurs while removing the event.
     */
    public RemoveEventResponseSuccess remove(String identifier) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/events/" + identifier, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, RemoveEventResponseSuccess.class);
    }

    /**
     * Sends an event to a contact.
     *
     * @param sendEventOptions The options for sending the event.
     * @return The response containing the sent event details.
     * @throws ResendException If an error occurs while sending the event.
     */
    public SendEventResponseSuccess send(SendEventOptions sendEventOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(sendEventOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/events/send", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, SendEventResponseSuccess.class);
    }
}
