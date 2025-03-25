package com.resend.services.broadcasts;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.service.BaseService;
import com.resend.services.broadcasts.model.*;
import okhttp3.MediaType;

/**
 *  Represents the Resend Broadcasts module.
 */
public class Broadcasts extends BaseService  {

    /**
     * Constructs an instance of the {@code Broadcasts} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Broadcasts(final String apiKey) {
        super(apiKey);

    }

    /**
     * Creates a Broadcast.
     *
     * @param createBroadcastOptions The Broadcast details.
     * @return The details of the created broadcast.
     * @throws ResendException If an error occurs during the Broadcast creation process.
     */
    public CreateBroadcastResponseSuccess create(CreateBroadcastOptions createBroadcastOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createBroadcastOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/broadcasts", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to create Broadcast: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateBroadcastResponseSuccess.class);
    }

    /**
     * Retrieves a broadcast by its unique identifier.
     *
     * @param id The unique identifier of the broadcast.
     * @return The retrieved broadcast details.
     * @throws ResendException If an error occurs while retrieving the broadcast.
     */
    public GetBroadcastResponseSuccess get(String id) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/broadcasts/" +id, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to retrieve broadcast: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, GetBroadcastResponseSuccess.class);
    }

    /**
     * Sends a Broadcast.
     *
     * @param sendBroadcastOptions The Broadcast details.
     * @param broadcastId The Broadcast id.
     * @return The details of the broadcast to be sent.
     * @throws ResendException If an error occurs during the Broadcast creation process.
     */
    public SendBroadcastResponseSuccess send(SendBroadcastOptions sendBroadcastOptions, String broadcastId) throws ResendException {
        String payload = super.resendMapper.writeValue(sendBroadcastOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/broadcasts/" +broadcastId + "/send", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to send broadcast: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, SendBroadcastResponseSuccess.class);
    }

    /**
     * Deletes a broadcast based on the provided broadcast ID.
     *
     * @param id The unique identifier of the broadcast to delete.
     * @return The RemoveBroadcastResponseSuccess with the details of the removed broadcast.
     * @throws ResendException If an error occurs during the broadcast deletion process.
     */
    public RemoveBroadcastResponseSuccess remove(String id) throws ResendException {
        AbstractHttpResponse<String> response = httpClient.perform("/broadcasts/" +id, super.apiKey, HttpMethod.DELETE, "", null);

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to delete broadcast: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, RemoveBroadcastResponseSuccess.class);
    }

    /**
     * Retrieves a list of broadcasts and returns a List.
     *
     * @return A ListBroadcastsResponseSuccess containing the list of broadcasts.
     * @throws ResendException If an error occurs during the broadcasts list retrieval process.
     */
    public ListBroadcastsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/broadcasts", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to retrieve broadcasts: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();

        return resendMapper.readValue(responseBody, ListBroadcastsResponseSuccess.class);
    }

    /**
     * Updates a Broadcast.
     *
     * @param updateBroadcastOptions The Broadcast details.
     * @return The details of the updated broadcast.
     * @throws ResendException If an error occurs during the Broadcast patching process.
     */
    public UpdateBroadcastResponseSuccess update(UpdateBroadcastOptions updateBroadcastOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateBroadcastOptions);
        AbstractHttpResponse<String> response = httpClient.perform("/broadcasts/"+updateBroadcastOptions.getId(), super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException("Failed to update Broadcast: " + response.getCode() + " " + response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, UpdateBroadcastResponseSuccess.class);
    }
}
