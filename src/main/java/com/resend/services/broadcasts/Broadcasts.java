package com.resend.services.broadcasts;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
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
     * Constructs an instance of the {@code Broadcasts} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Broadcasts(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates a Broadcast.
     *
     * @param createBroadcastOptions The Broadcast details.
     * @return The details of the created broadcast.
     * @throws ResendException If an error occurs during the Broadcast creation process.
     */
    public CreateBroadcastResponseSuccess create(CreateBroadcastOptions createBroadcastOptions) throws ResendException {
        return create(createBroadcastOptions, (RequestOptions) null);
    }

    /**
     * Creates a Broadcast.
     *
     * @param createBroadcastOptions The Broadcast details.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The details of the created broadcast.
     * @throws ResendException If an error occurs during the Broadcast creation process.
     */
    public CreateBroadcastResponseSuccess create(CreateBroadcastOptions createBroadcastOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createBroadcastOptions);
        return execute("/broadcasts", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateBroadcastResponseSuccess.class);
    }

    /**
     * Retrieves a broadcast by its unique identifier.
     *
     * @param id The unique identifier of the broadcast.
     * @return The retrieved broadcast details.
     * @throws ResendException If an error occurs while retrieving the broadcast.
     */
    public GetBroadcastResponseSuccess get(String id) throws ResendException {
        return get(id, (RequestOptions) null);
    }

    /**
     * Retrieves a broadcast by its unique identifier.
     *
     * @param id The unique identifier of the broadcast.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The retrieved broadcast details.
     * @throws ResendException If an error occurs while retrieving the broadcast.
     */
    public GetBroadcastResponseSuccess get(String id, RequestOptions requestOptions) throws ResendException {
        return execute("/broadcasts/" +id, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, GetBroadcastResponseSuccess.class);
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
        return send(sendBroadcastOptions, broadcastId, (RequestOptions) null);
    }

    /**
     * Sends a Broadcast.
     *
     * @param sendBroadcastOptions The Broadcast details.
     * @param broadcastId The Broadcast id.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The details of the broadcast to be sent.
     * @throws ResendException If an error occurs during the Broadcast creation process.
     */
    public SendBroadcastResponseSuccess send(SendBroadcastOptions sendBroadcastOptions, String broadcastId, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(sendBroadcastOptions);
        return execute("/broadcasts/" +broadcastId + "/send", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, SendBroadcastResponseSuccess.class);
    }

    /**
     * Cancels a queued or scheduled broadcast.
     *
     * @param id The unique identifier of the broadcast to cancel.
     * @return The CancelBroadcastResponseSuccess with the details of the canceled broadcast.
     * @throws ResendException If an error occurs during the broadcast cancellation process.
     */
    public CancelBroadcastResponseSuccess cancel(String id) throws ResendException {
        return cancel(id, (RequestOptions) null);
    }

    /**
     * Cancels a queued or scheduled broadcast.
     *
     * @param id The unique identifier of the broadcast to cancel.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The CancelBroadcastResponseSuccess with the details of the canceled broadcast.
     * @throws ResendException If an error occurs during the broadcast cancellation process.
     */
    public CancelBroadcastResponseSuccess cancel(String id, RequestOptions requestOptions) throws ResendException {
        return execute("/broadcasts/" + id + "/cancel", HttpMethod.POST, "", MediaType.get("application/json"), requestOptions, CancelBroadcastResponseSuccess.class);
    }

    /**
     * Duplicates a broadcast into a new draft.
     *
     * @param id The unique identifier of the broadcast to duplicate.
     * @return The DuplicateBroadcastResponseSuccess with the identifier of the new draft.
     * @throws ResendException If an error occurs during the broadcast duplication process.
     */
    public DuplicateBroadcastResponseSuccess duplicate(String id) throws ResendException {
        return duplicate(id, (RequestOptions) null);
    }

    /**
     * Duplicates a broadcast into a new draft.
     *
     * @param id The unique identifier of the broadcast to duplicate.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The DuplicateBroadcastResponseSuccess with the identifier of the new draft.
     * @throws ResendException If an error occurs during the broadcast duplication process.
     */
    public DuplicateBroadcastResponseSuccess duplicate(String id, RequestOptions requestOptions) throws ResendException {
        return execute("/broadcasts/" + id + "/duplicate", HttpMethod.POST, "", MediaType.get("application/json"), requestOptions, DuplicateBroadcastResponseSuccess.class);
    }

    /**
     * Deletes a broadcast based on the provided broadcast ID.
     *
     * @param id The unique identifier of the broadcast to delete.
     * @return The RemoveBroadcastResponseSuccess with the details of the removed broadcast.
     * @throws ResendException If an error occurs during the broadcast deletion process.
     */
    public RemoveBroadcastResponseSuccess remove(String id) throws ResendException {
        return remove(id, (RequestOptions) null);
    }

    /**
     * Deletes a broadcast based on the provided broadcast ID.
     *
     * @param id The unique identifier of the broadcast to delete.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The RemoveBroadcastResponseSuccess with the details of the removed broadcast.
     * @throws ResendException If an error occurs during the broadcast deletion process.
     */
    public RemoveBroadcastResponseSuccess remove(String id, RequestOptions requestOptions) throws ResendException {
        return execute("/broadcasts/" +id, HttpMethod.DELETE, "", null, requestOptions, RemoveBroadcastResponseSuccess.class);
    }

    /**
     * Retrieves a list of broadcasts and returns a List.
     *
     * @return A ListBroadcastsResponseSuccess containing the list of broadcasts.
     * @throws ResendException If an error occurs during the broadcasts list retrieval process.
     */
    public ListBroadcastsResponseSuccess list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Retrieves a list of broadcasts and returns a List.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListBroadcastsResponseSuccess containing the list of broadcasts.
     * @throws ResendException If an error occurs during the broadcasts list retrieval process.
     */
    public ListBroadcastsResponseSuccess list(RequestOptions requestOptions) throws ResendException {
        return execute("/broadcasts", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListBroadcastsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of broadcasts and returns a List.
     * @param params The params used to customize the list.
     *
     * @return A ListBroadcastsResponseSuccess containing the paginated list of broadcasts.
     * @throws ResendException If an error occurs during the broadcasts list retrieval process.
     */
    public ListBroadcastsResponseSuccess list(ListParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of broadcasts and returns a List.
     * @param params The params used to customize the list.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListBroadcastsResponseSuccess containing the paginated list of broadcasts.
     * @throws ResendException If an error occurs during the broadcasts list retrieval process.
     */
    public ListBroadcastsResponseSuccess list(ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/broadcasts" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListBroadcastsResponseSuccess.class);
    }

    /**
     * Retrieves the recipients of a broadcast for a given event type, such as who opened,
     * clicked, or bounced.
     *
     * @param id The unique identifier of the broadcast.
     * @param params The params used to filter and paginate the recipients.
     * @return A ListBroadcastRecipientsResponseSuccess containing the paginated list of recipients.
     * @throws ResendException If an error occurs during the broadcast recipients retrieval process.
     */
    public ListBroadcastRecipientsResponseSuccess recipients(String id, ListBroadcastRecipientsParams params) throws ResendException {
        return recipients(id, params, (RequestOptions) null);
    }

    /**
     * Retrieves the recipients of a broadcast for a given event type, such as who opened,
     * clicked, or bounced.
     *
     * @param id The unique identifier of the broadcast.
     * @param params The params used to filter and paginate the recipients.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListBroadcastRecipientsResponseSuccess containing the paginated list of recipients.
     * @throws ResendException If an error occurs during the broadcast recipients retrieval process.
     */
    public ListBroadcastRecipientsResponseSuccess recipients(String id, ListBroadcastRecipientsParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/broadcasts/" + id + "/recipients" + params.toQueryString();
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListBroadcastRecipientsResponseSuccess.class);
    }

    /**
     * Retrieves a broadcast's clicked links.
     *
     * @param id The unique identifier of the broadcast.
     * @return A ListBroadcastClickedLinksResponseSuccess containing the broadcast's clicked links.
     * @throws ResendException If an error occurs during the clicked links retrieval process.
     */
    public ListBroadcastClickedLinksResponseSuccess clickedLinks(String id) throws ResendException {
        return clickedLinks(id, (RequestOptions) null);
    }

    /**
     * Retrieves a broadcast's clicked links.
     *
     * @param id The unique identifier of the broadcast.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListBroadcastClickedLinksResponseSuccess containing the broadcast's clicked links.
     * @throws ResendException If an error occurs during the clicked links retrieval process.
     */
    public ListBroadcastClickedLinksResponseSuccess clickedLinks(String id, RequestOptions requestOptions) throws ResendException {
        return execute("/broadcasts/" + id + "/clicked-links", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListBroadcastClickedLinksResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of a broadcast's clicked links.
     *
     * @param id The unique identifier of the broadcast.
     * @param params The params used to customize the list.
     *
     * @return A ListBroadcastClickedLinksResponseSuccess containing the paginated list of clicked links.
     * @throws ResendException If an error occurs during the clicked links retrieval process.
     */
    public ListBroadcastClickedLinksResponseSuccess clickedLinks(String id, ListParams params) throws ResendException {
        return clickedLinks(id, params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of a broadcast's clicked links.
     *
     * @param id The unique identifier of the broadcast.
     * @param params The params used to customize the list.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListBroadcastClickedLinksResponseSuccess containing the paginated list of clicked links.
     * @throws ResendException If an error occurs during the clicked links retrieval process.
     */
    public ListBroadcastClickedLinksResponseSuccess clickedLinks(String id, ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/broadcasts/" + id + "/clicked-links" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListBroadcastClickedLinksResponseSuccess.class);
    }

    /**
     * Updates a Broadcast.
     *
     * @param updateBroadcastOptions The Broadcast details.
     * @return The details of the updated broadcast.
     * @throws ResendException If an error occurs during the Broadcast patching process.
     */
    public UpdateBroadcastResponseSuccess update(UpdateBroadcastOptions updateBroadcastOptions) throws ResendException {
        return update(updateBroadcastOptions, (RequestOptions) null);
    }

    /**
     * Updates a Broadcast.
     *
     * @param updateBroadcastOptions The Broadcast details.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The details of the updated broadcast.
     * @throws ResendException If an error occurs during the Broadcast patching process.
     */
    public UpdateBroadcastResponseSuccess update(UpdateBroadcastOptions updateBroadcastOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateBroadcastOptions);
        return execute("/broadcasts/"+updateBroadcastOptions.getId(), HttpMethod.PATCH, payload, MediaType.get("application/json"), requestOptions, UpdateBroadcastResponseSuccess.class);
    }
}
