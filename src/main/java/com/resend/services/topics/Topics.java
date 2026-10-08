package com.resend.services.topics;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import com.resend.services.topics.model.*;
import okhttp3.MediaType;

/**
 * Represents the Resend Topics module.
 */
public final class Topics extends BaseService {

    /**
     * Constructs an instance of the {@code Topics} class.
     *
     * @param apiKey The apiKey used for authentication.
     */
    public Topics(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs an instance of the {@code Topics} class that sends requests through the given HTTP client.
     *
     * @param apiKey     The apiKey used for authentication.
     * @param httpClient The HTTP client to use.
     */
    public Topics(final String apiKey, final IHttpClient<String> httpClient) {
        super(apiKey, httpClient);
    }

    /**
     * Creates a new topic.
     *
     * @param createTopicOptions The request containing topic details.
     * @return The response indicating the status of the topic creation.
     * @throws ResendException If an error occurs while creating the topic.
     */
    public CreateTopicResponseSuccess create(CreateTopicOptions createTopicOptions) throws ResendException {
        return create(createTopicOptions, (RequestOptions) null);
    }

    /**
     * Creates a new topic.
     *
     * @param createTopicOptions The request containing topic details.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response indicating the status of the topic creation.
     * @throws ResendException If an error occurs while creating the topic.
     */
    public CreateTopicResponseSuccess create(CreateTopicOptions createTopicOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createTopicOptions);
        return execute("/topics", HttpMethod.POST, payload, MediaType.get("application/json"), requestOptions, CreateTopicResponseSuccess.class);
    }

    /**
     * Retrieves a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @return The retrieved topic's details.
     * @throws ResendException If an error occurs while retrieving the topic.
     */
    public GetTopicResponseSuccess get(String topicId) throws ResendException {
        return get(topicId, (RequestOptions) null);
    }

    /**
     * Retrieves a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The retrieved topic's details.
     * @throws ResendException If an error occurs while retrieving the topic.
     */
    public GetTopicResponseSuccess get(String topicId, RequestOptions requestOptions) throws ResendException {
        return execute("/topics/" + topicId, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, GetTopicResponseSuccess.class);
    }

    /**
     * Updates a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @param updateTopicOptions The new data for the topic.
     * @return The response indicating the status of the topic update.
     * @throws ResendException If an error occurs while updating the topic.
     */
    public UpdateTopicResponseSuccess update(String topicId, UpdateTopicOptions updateTopicOptions) throws ResendException {
        return update(topicId, updateTopicOptions, (RequestOptions) null);
    }

    /**
     * Updates a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @param updateTopicOptions The new data for the topic.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response indicating the status of the topic update.
     * @throws ResendException If an error occurs while updating the topic.
     */
    public UpdateTopicResponseSuccess update(String topicId, UpdateTopicOptions updateTopicOptions, RequestOptions requestOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(updateTopicOptions);
        return execute("/topics/" + topicId, HttpMethod.PATCH, payload, MediaType.get("application/json"), requestOptions, UpdateTopicResponseSuccess.class);
    }

    /**
     * Removes a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @return The response indicating the status of the topic removal.
     * @throws ResendException If an error occurs while removing the topic.
     */
    public RemoveTopicResponseSuccess remove(String topicId) throws ResendException {
        return remove(topicId, (RequestOptions) null);
    }

    /**
     * Removes a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return The response indicating the status of the topic removal.
     * @throws ResendException If an error occurs while removing the topic.
     */
    public RemoveTopicResponseSuccess remove(String topicId, RequestOptions requestOptions) throws ResendException {
        return execute("/topics/" + topicId, HttpMethod.DELETE, null, MediaType.get("application/json"), requestOptions, RemoveTopicResponseSuccess.class);
    }

    /**
     * Retrieves a list of topics and returns a List.
     *
     * @return A ListTopicsResponse containing the list of topics.
     * @throws ResendException If an error occurs during the topics list retrieval process.
     */
    public ListTopicsResponseSuccess list() throws ResendException {
        return list((RequestOptions) null);
    }

    /**
     * Retrieves a list of topics and returns a List.
     *
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListTopicsResponse containing the list of topics.
     * @throws ResendException If an error occurs during the topics list retrieval process.
     */
    public ListTopicsResponseSuccess list(RequestOptions requestOptions) throws ResendException {
        return execute("/topics", HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListTopicsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of topics and returns a List.
     *
     * @param params The params used to customize the list.
     * @return A ListTopicsResponse containing the paginated list of topics.
     * @throws ResendException If an error occurs during the topics list retrieval process.
     */
    public ListTopicsResponseSuccess list(ListParams params) throws ResendException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Retrieves a paginated list of topics and returns a List.
     *
     * @param params The params used to customize the list.
     * @param requestOptions The per-request options (timeout, retries, idempotency key, headers), or {@code null} for none.
     * @return A ListTopicsResponse containing the paginated list of topics.
     * @throws ResendException If an error occurs during the topics list retrieval process.
     */
    public ListTopicsResponseSuccess list(ListParams params, RequestOptions requestOptions) throws ResendException {
        String pathWithQuery = "/topics" + URLHelper.parse(params);
        return execute(pathWithQuery, HttpMethod.GET, null, MediaType.get("application/json"), requestOptions, ListTopicsResponseSuccess.class);
    }
}
