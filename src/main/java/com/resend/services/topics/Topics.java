package com.resend.services.topics;

import com.resend.core.exception.ResendException;
import com.resend.core.helper.URLHelper;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.ListParams;
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
     * Creates a new topic.
     *
     * @param createTopicOptions The request containing topic details.
     * @return The response indicating the status of the topic creation.
     * @throws ResendException If an error occurs while creating the topic.
     */
    public CreateTopicResponseSuccess create(CreateTopicOptions createTopicOptions) throws ResendException {
        String payload = super.resendMapper.writeValue(createTopicOptions);
        AbstractHttpResponse<String> response = super.httpClient.perform("/topics", super.apiKey, HttpMethod.POST, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, CreateTopicResponseSuccess.class);
    }

    /**
     * Retrieves a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @return The retrieved topic's details.
     * @throws ResendException If an error occurs while retrieving the topic.
     */
    public GetTopicResponseSuccess get(String topicId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/topics/" + topicId, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, GetTopicResponseSuccess.class);
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
        String payload = super.resendMapper.writeValue(updateTopicOptions);
        AbstractHttpResponse<String> response = this.httpClient.perform("/topics/" + topicId, super.apiKey, HttpMethod.PATCH, payload, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, UpdateTopicResponseSuccess.class);
    }

    /**
     * Removes a topic by its unique identifier.
     *
     * @param topicId The unique identifier of the topic.
     * @return The response indicating the status of the topic removal.
     * @throws ResendException If an error occurs while removing the topic.
     */
    public RemoveTopicResponseSuccess remove(String topicId) throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/topics/" + topicId, super.apiKey, HttpMethod.DELETE, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, RemoveTopicResponseSuccess.class);
    }

    /**
     * Retrieves a list of topics and returns a List.
     *
     * @return A ListTopicsResponse containing the list of topics.
     * @throws ResendException If an error occurs during the topics list retrieval process.
     */
    public ListTopicsResponseSuccess list() throws ResendException {
        AbstractHttpResponse<String> response = this.httpClient.perform("/topics", super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListTopicsResponseSuccess.class);
    }

    /**
     * Retrieves a paginated list of topics and returns a List.
     *
     * @param params The params used to customize the list.
     * @return A ListTopicsResponse containing the paginated list of topics.
     * @throws ResendException If an error occurs during the topics list retrieval process.
     */
    public ListTopicsResponseSuccess list(ListParams params) throws ResendException {
        String pathWithQuery = "/topics" + URLHelper.parse(params);
        AbstractHttpResponse<String> response = this.httpClient.perform(pathWithQuery, super.apiKey, HttpMethod.GET, null, MediaType.get("application/json"));

        if (!response.isSuccessful()) {
            throw new ResendException(response.getCode(), response.getBody());
        }

        String responseBody = response.getBody();
        return resendMapper.readValue(responseBody, ListTopicsResponseSuccess.class);
    }
}
